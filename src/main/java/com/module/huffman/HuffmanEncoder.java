package com.module.huffman;

import com.module.files.BitFileWriter;
import com.module.files.SymbolFileReader;
import com.module.files.SymbolType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;

public class HuffmanEncoder {


    private FrequencyTable frequencyTable;

    private BitFileWriter bitFileWriter;
    private SymbolFileReader symbolFileReader;

    public HuffmanEncoder(Path pathToFile) throws FileNotFoundException {
        this.symbolFileReader = new SymbolFileReader(SymbolType.BYTE,pathToFile);
        Path pathToOutput =  Paths.get(pathToFile.getFileName().toString().split("\\.")[0] + ".hf");

        this.bitFileWriter = new BitFileWriter(pathToOutput);
    }

    public void encode() throws IOException {
        buildFrequencyTable();
        makeTreeFromFrequencyTable();
        HuffmanCodeTree tree = makeTreeFromFrequencyTable();
        HuffmanCodeTable codeTable = new HuffmanCodeTable(tree.walkAndMap());
        saveCodeTable(codeTable);
        makeCodedFile(codeTable);
    }

    public void buildFrequencyTable() throws IOException {
        frequencyTable = new FrequencyTable();

        int nextSymbol;
        while ((nextSymbol = symbolFileReader.readNextSymbol())>0) {
            frequencyTable.append(nextSymbol);
        }
        placeEOF(frequencyTable);
    }

    public void placeEOF(FrequencyTable frequencyTable){
        for(int i=0;i<256;i++) {
            int finalI = i;
            if(frequencyTable.getFrequencies().stream().noneMatch(item->item.symbol == finalI))
            {
                frequencyTable.append(i);
                break;
            }
        }
    }

    public HuffmanCodeTree makeTreeFromFrequencyTable(){
        PriorityQueue<TreeNode> priorityQueue = frequencyTable.toQueueOfTreeNodes();
        while (priorityQueue.size() != 1) {
            TreeNode lowestNode = priorityQueue.poll();
            TreeNode pairLowestNode = priorityQueue.poll();

            TreeNode newRoot = new TreeNode(lowestNode.count + pairLowestNode.count, 0);
            newRoot.setLeft(pairLowestNode);
            newRoot.setRight(lowestNode);
            priorityQueue.add(newRoot);
        }

        return new HuffmanCodeTree(priorityQueue.poll());
    }

    public void makeCodedFile(HuffmanCodeTable codeTable) throws IOException {
        int nextSymbol;
        symbolFileReader.reopenInputStream();
        while ((nextSymbol = symbolFileReader.readNextSymbol())>0){
            HuffmanCode huffmanCode = codeTable.getBySymbol(nextSymbol);
            bitFileWriter.appendHuffmanCodeToFile(huffmanCode.code, huffmanCode.length, huffmanCode.length);
        }
        HuffmanCode eofCode = codeTable.getTable().get(codeTable.getTable().size()-1);
        bitFileWriter.appendHuffmanCodeToFile(eofCode.code,eofCode.length,eofCode.length);
        bitFileWriter.flushAndClose();
    }

    public void saveCodeTable(HuffmanCodeTable codeTable){

    }
}
