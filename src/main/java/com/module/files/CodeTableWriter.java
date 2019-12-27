package com.module.files;

import com.module.huffman.HuffmanCode;
import com.module.huffman.HuffmanCodeTable;

import java.io.*;
import java.nio.file.Path;

public class CodeTableWriter {

    private BufferedOutputStream bufferedOutputStream;
    private Path pathToFile;

    public CodeTableWriter(Path pathToFile) throws IOException {
        this.pathToFile = pathToFile;
        this.bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(pathToFile.toFile(), true));
        clearFile();
    }

    public void writeInSingleFile(HuffmanCodeTable codeTable) throws IOException {
        for (HuffmanCode huffmanCode : codeTable.getTable()) {
            bufferedOutputStream.write(huffmanCode.original);
            bufferedOutputStream.write(huffmanCode.code);
        }
    }

    public void writeInSeparatelyFile(HuffmanCodeTable codeTable) throws IOException {
        this.bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(pathToFile.getFileName().toString().split("\\.")[0] + ".dictionary", true));
        for (HuffmanCode huffmanCode : codeTable.getTable()) {
            bufferedOutputStream.write(huffmanCode.original);
            bufferedOutputStream.write(huffmanCode.code);
        }
    }
    public void clearFile() throws IOException{
        new PrintWriter(bufferedOutputStream).close();
    }
}
