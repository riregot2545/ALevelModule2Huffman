package com.module.huffman;

import com.module.files.BitFileReader;
import com.module.files.SymbolFileReader;
import com.module.files.SymbolType;

import java.io.*;
import java.nio.file.Path;

public class HuffmanDecoder {

    BitFileReader bitFileReader;
    public HuffmanDecoder(Path pathToFile) throws FileNotFoundException {
        bitFileReader = new BitFileReader(pathToFile);
    }

    public void decode() {

    }

    public HuffmanCodeTree restoreTree() throws IOException {
        int byteOriginal = bitFileReader.next(8);
        int byteCode = bitFileReader.next(8);
        return null;
    }
}
