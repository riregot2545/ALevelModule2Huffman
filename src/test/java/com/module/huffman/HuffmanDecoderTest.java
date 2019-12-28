package com.module.huffman;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class HuffmanDecoderTest {

    HuffmanDecoder decoder = new HuffmanDecoder(Paths.get("C:\\ALevel\\ALevelModule2Huffman\\Филипп Джеймс Исход.hf"));

    public HuffmanDecoderTest() throws FileNotFoundException {
    }

    @Test
    public void decode() throws IOException {
        decoder.decode();
    }
}