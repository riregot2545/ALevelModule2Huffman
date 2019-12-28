package com.module.files;

import com.module.huffman.FrequencyTable;
import com.module.huffman.HuffmanCode;
import com.module.huffman.HuffmanCodeTable;
import com.module.utils.ByteUtils;
import com.module.utils.FilePathsUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FrequencyTableWriter {

    private BufferedOutputStream bufferedOutputStream;
    private Path pathToFile;

    public FrequencyTableWriter(Path pathToFile) {
        this.pathToFile = pathToFile;
    }

    public void writeInSingleFile(FrequencyTable frequencyTable) throws IOException {
        createAndClearFile(pathToFile);

        this.bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(pathToFile.toFile(), true));
        write(frequencyTable);

        //write repeated symbol to separate table and coded file
        bufferedOutputStream.write(frequencyTable.getFrequencies().get(frequencyTable.size()-1).symbol);
        flushAndClose();
    }

    public void writeInSeparatelyFile(FrequencyTable frequencyTable) throws IOException {
        Path pathToDictionary = FilePathsUtils.switchExtension(pathToFile, "dictionary");
        createAndClearFile(pathToDictionary);

        this.bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(pathToDictionary.toFile(), true));
        write(frequencyTable);
        flushAndClose();
    }

    private void write(FrequencyTable frequencyTable) throws IOException {
        for (FrequencyTable.SymbolFrequency frequency : frequencyTable.getFrequencies()) {
            bufferedOutputStream.write(frequency.symbol);
            byte[] bytes = ByteUtils.intToByteArray(frequency.frequency);
            bufferedOutputStream.write(bytes);
        }
        bufferedOutputStream.flush();
    }

    private void createAndClearFile(Path path) throws IOException {
        new PrintWriter(new FileOutputStream(path.toFile())).close();
    }

    private void flushAndClose() throws IOException {
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
}
