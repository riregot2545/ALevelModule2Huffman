package com.module.huffman;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class HuffmanCodeTable {
    private List<HuffmanCode> table;

    public HuffmanCodeTable(List<HuffmanCode> table) {
        this.table = table;
    }

    public HuffmanCodeTable() {
        this.table = new ArrayList<>();
    }

    public HuffmanCode getBySymbol(int symbol) {
        Optional<HuffmanCode> first = table.stream().filter(huffmanCode -> huffmanCode.original == symbol).findFirst();
        if (first.isPresent())
            return first.get();
        else
            throw new IllegalArgumentException("Symbol is not contained in huffman table");
    }

    public HuffmanCode getByCode(int code, int length) {
        Optional<HuffmanCode> first = table.stream().filter(huffmanCode -> huffmanCode.code == code
                && huffmanCode.length == length).findFirst();
        if (first.isPresent())
            return first.get();
        else
            throw new IllegalArgumentException("Symbol is not contained in huffman table");
    }

    public int getMinCodeLenght() {
        Optional<HuffmanCode> minOpt = table.stream().min(((o1, o2) -> Integer.compare(o1.length, o2.length)));
        if (minOpt.isPresent())
            return minOpt.get().length;
        throw new IllegalArgumentException("Table is empty");
    }
}
