package com.module.huffman;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TreeNode {
    public int count;
    public int symbol;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int count, int symbol) {
        this.count = count;
        this.symbol = symbol;
    }
}