package com.module.huffman;

import com.module.files.BitFileReader;
import com.module.files.FrequencyTableReader;
import com.module.files.HuffmanDecompressor;
import com.module.utils.Constants;
import com.module.utils.FilePathsUtils;
import com.module.utils.FrequencyTableSavingType;

import java.io.*;
import java.nio.file.Path;

public class HuffmanDecoder {

    private HuffmanDecompressor huffmanDecompressor;
    private FrequencyTableReader frequencyTableReader;

    private Path pathToSourceFile;
    private Path pathToOutputFile;

    public HuffmanDecoder(Path pathToFile) throws FileNotFoundException {
        this.pathToSourceFile = pathToFile;
        this.pathToOutputFile = FilePathsUtils.deleteExtension(pathToFile);

        if(Constants.FREQUENCY_TABLE_SAVING_TYPE == FrequencyTableSavingType.IN_OUTPUT_FILE)
            this.frequencyTableReader = new FrequencyTableReader(pathToFile);
        else {
            Path dictionaryPath = FilePathsUtils.switchExtension(pathToFile,"dictionary");
            this.frequencyTableReader = new FrequencyTableReader(dictionaryPath);
        }
    }

    public void decode() throws IOException {
        FrequencyTable frequencyTable = frequencyTableReader.readCoded();
        HuffmanCodeTree huffmanCodeTree =
                new HuffmanCodeTree.CodeTreeBuilder().fromFrequencyTable(frequencyTable);
        huffmanCodeTree.walkAndMap();
        HuffmanCodeTable codeTable = new HuffmanCodeTable(huffmanCodeTree.walkAndMap());

        long seekOfTable;
        if (Constants.FREQUENCY_TABLE_SAVING_TYPE == FrequencyTableSavingType.IN_OUTPUT_FILE)
            seekOfTable = frequencyTable.countBytes();
        else
            seekOfTable = 0;
        this.huffmanDecompressor = new HuffmanDecompressor(pathToSourceFile, pathToOutputFile, seekOfTable);
        huffmanDecompressor.replaceHuffmanByOriginal(codeTable);
        System.out.println();
    }
}
