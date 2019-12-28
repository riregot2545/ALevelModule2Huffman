package com.module.files;

import com.module.huffman.HuffmanCode;
import com.module.huffman.HuffmanCodeTable;

import java.io.*;
import java.nio.file.Path;

public class HuffmanDecompresser {

    private BitFileReader bitFileReader;
    private BufferedOutputStream bufferedOutputStream;

    public HuffmanDecompresser(Path pathToSource, Path pathToOutput, long seek) throws IOException {
        this.bitFileReader = new BitFileReader(pathToSource, seek);
        this.bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(pathToOutput.toFile()));
    }

    public void replaceHuffmanByOriginal(HuffmanCodeTable codeTable) throws IOException {
        int nextSymbol;

        while ((nextSymbol = bitFileReader.next())>0) {
            HuffmanCode huffmanCode = codeTable.getBySymbol(nextSymbol);
            bitFileWriter.appendHuffmanCodeToFile(huffmanCode);
        }
        HuffmanCode eofCode = codeTable.getTable().get(codeTable.getTable().size() - 1);
        bitFileWriter.appendValueToFile(eofCode.code, eofCode.length, eofCode.length);
        bitFileWriter.flushAndClose();
    }
}
