package com.module.huffman;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Getter
public class FrequencyTable {
    private List<SymbolFrequency> frequencies;

    public FrequencyTable() {
        frequencies = new ArrayList<>();
    }


    public void append(int symbol) {
        if (frequencies.stream().anyMatch(item -> item.symbol == symbol)) {
            frequencies.stream().filter(item -> item.symbol == symbol).forEach(item -> item.frequency++);
        } else {
            frequencies.add(new SymbolFrequency(symbol));
        }
    }

    public PriorityQueue<TreeNode> toQueueOfTreeNodes() {
        PriorityQueue<TreeNode> priorityQueue = new PriorityQueue<>(new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return Integer.compare(o1.count, o2.count);
            }
        });
        frequencies.forEach(item -> priorityQueue.add(new TreeNode(item.frequency, item.symbol)));
        return priorityQueue;
    }


    public static class SymbolFrequency {
        public int symbol;
        public int frequency;

        public SymbolFrequency(int symbol) {
            this.symbol = symbol;
            this.frequency = 1;
        }
    }
}
