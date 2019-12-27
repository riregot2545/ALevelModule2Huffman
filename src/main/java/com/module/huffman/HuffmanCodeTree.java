package com.module.huffman;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class HuffmanCodeTree {
    public TreeNode root;

    public List<HuffmanCode> walkAndMap() {
        List<HuffmanCode> huffmanCodes = new ArrayList<>();
        HuffmanCode huffmanCode = new HuffmanCode();
        walkAndMap(huffmanCodes, huffmanCode, root);
        return huffmanCodes;
    }

    private void walkAndMap(List<HuffmanCode> huffmanCodes, HuffmanCode huffmanCode, TreeNode newRoot) {
        if (newRoot.left != null && newRoot.right != null) {
            HuffmanCode leftCode = huffmanCode;
            HuffmanCode rightCode = huffmanCode.clone();
            leftCode.appendBit(0);
            rightCode.appendBit(1);

            walkAndMap(huffmanCodes, leftCode, newRoot.left);
            walkAndMap(huffmanCodes, rightCode, newRoot.right);
        } else {
            huffmanCode.original = newRoot.symbol;
            huffmanCodes.add(huffmanCode);
        }
    }

}