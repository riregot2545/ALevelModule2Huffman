package com.module.files;

import com.module.huffman.HuffmanCode;
import com.module.huffman.HuffmanCodeTable;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class CodeTableWriter {

    private BufferedOutputStream bufferedOutputStream;

    public CodeTableWriter(Path pathToFile) throws FileNotFoundException {
        this.bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(pathToFile.toFile(),true));
    }

    public void write(HuffmanCodeTable codeTable) throws IOException {
        for (HuffmanCode huffmanCode : codeTable.getTable()) {
            bufferedOutputStream.write(huffmanCode.original);
            bufferedOutputStream.write(huffmanCode.code);
        }
    }

}
