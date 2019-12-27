package com.module.files;

import com.module.huffman.HuffmanCodeTable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class CodeTableRestorer {

    BitFileReader bitFileReader;

    public CodeTableRestorer(Path path) throws FileNotFoundException {
        this.bitFileReader = new BitFileReader(path);
    }

    public HuffmanCodeTable restore(){
        throw new NotImplementedException();
    }
}
