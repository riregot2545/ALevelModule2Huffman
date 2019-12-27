package com.module.huffman;

import com.module.files.BitFileReader;
import com.module.files.CodeTableRestorer;
import com.module.files.SymbolFileReader;
import com.module.files.SymbolType;

import java.io.*;
import java.nio.file.Path;

public class HuffmanDecoder {

    BitFileReader bitFileReader;
    CodeTableRestorer codeTableRestorer;

    public HuffmanDecoder(Path pathToFile) throws FileNotFoundException {
        bitFileReader = new BitFileReader(pathToFile);
        codeTableRestorer = new CodeTableRestorer(pathToFile);
    }

    public void decode() throws IOException {
        HuffmanCodeTable huffmanCodeTable = restoreTable();

    }


    public HuffmanCodeTable restoreTable() throws IOException {
       return codeTableRestorer.restore();
    }
}
