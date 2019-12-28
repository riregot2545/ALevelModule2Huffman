package com.module.files;

import com.module.huffman.FrequencyTable;
import com.module.huffman.HuffmanCode;
import com.module.huffman.HuffmanCodeTable;
import com.module.utils.ByteUtils;
import com.module.utils.Constants;
import com.module.utils.FrequencyTableSavingType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FrequencyTableReader {

    ByteFileReader byteFileReader;

    public FrequencyTableReader(Path path) throws FileNotFoundException {
        this.byteFileReader = new ByteFileReader(Constants.READING_SYMBOL_TYPE,path);
    }

    public FrequencyTable readCoded() throws IOException {
        FrequencyTable frequencyTable = new FrequencyTable();
        while (true) {
            int nextSymbol = byteFileReader.readNextSymbol();
            byte[] bytes = byteFileReader.readNextBytes(4);
            if(Constants.FREQUENCY_TABLE_SAVING_TYPE == FrequencyTableSavingType.IN_OUTPUT_FILE) {
                if (frequencyTable.containsSymbol(nextSymbol))
                    break;
            }else if(bytes.length!=4)
                break;


            int nextFrequency = ByteUtils.byteArrayToInt(bytes);
            frequencyTable.append(nextSymbol,nextFrequency);
        }
        return frequencyTable;
    }

    public FrequencyTable makeFromSource() throws IOException {
        FrequencyTable frequencyTable = new FrequencyTable();

        int nextSymbol;
        while ((nextSymbol = byteFileReader.readNextSymbol()) != Integer.MIN_VALUE) {
            frequencyTable.append(nextSymbol);
        }
        return frequencyTable;
    }
}
