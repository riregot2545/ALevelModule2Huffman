package com.module.files;

import com.module.huffman.HuffmanCode;
import com.module.huffman.HuffmanCodeTable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CodeTableRestorer {

    BitFileReader bitFileReader;

    public CodeTableRestorer(Path path) throws FileNotFoundException {
        this.bitFileReader = new BitFileReader(path);
    }

    public HuffmanCodeTable restore() throws IOException {
        HuffmanCodeTable huffmanCodeTable = new HuffmanCodeTable();
        List<HuffmanCode> huffmanCodeList = new ArrayList<>();
        while (true) {
            HuffmanCode huffmanCode = new HuffmanCode();

            huffmanCode.original = bitFileReader.next(8);
            huffmanCode.code = bitFileReader.next(8);

            if(huffmanCodeList.stream().anyMatch(item->item.code==huffmanCode.code && huffmanCode.code!=0))
                break;
            huffmanCodeList.add(huffmanCode);
        }
        return huffmanCodeTable;
    }
}
