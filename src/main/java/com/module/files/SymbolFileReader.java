package com.module.files;

import com.module.huffman.HuffmanCode;
import com.module.utils.BitUtils;

import java.io.*;
import java.nio.file.Path;

public class SymbolFileReader {

    private Path pathToFile;
    private int symbolSize;
    private InputStream inputStream;
    public SymbolFileReader(SymbolType symbolType, Path pathToFile) throws FileNotFoundException {
        switch (symbolType){
            case BYTE:
                symbolSize = 1;
                break;
            case WORD:
                symbolSize = 2;
                break;
            case DWORD:
                symbolSize = 4;
                break;
        }
        this.inputStream = new BufferedInputStream(new FileInputStream(pathToFile.toFile()));;
    }

    public int readNextSymbol() throws IOException {
        if(inputStream.available() > 0) {
            byte[] bytes = new byte[symbolSize];
            inputStream.read(bytes);
            return BitUtils.merge(bytes);
        }else return -1;
    }

    public void reopenInputStream() throws FileNotFoundException {
        inputStream = new BufferedInputStream(new FileInputStream(pathToFile.toFile()));
    }

}