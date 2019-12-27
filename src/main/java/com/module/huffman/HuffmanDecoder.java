package com.module.huffman;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public class HuffmanDecoder {

    BufferedInputStream bufferedInputStream;

    public HuffmanDecoder(Path pathToFile) throws FileNotFoundException {

        bufferedInputStream = new BufferedInputStream(new FileInputStream(pathToFile.toFile()));

    }

    public void decode() {

    }
}
