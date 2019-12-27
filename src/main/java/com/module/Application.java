package com.module;

import com.module.huffman.HuffmanDecoder;
import com.module.huffman.HuffmanEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private static HuffmanEncoder huffmanEncoder;
    private static HuffmanDecoder huffmanDecoder;

    public static void main(String[] args) {
        Path fileToProc;
        if (args.length==0) {
            System.exit(-500);
        } else {
            fileToProc = Paths.get(args[0]);
            System.out.println();
            String extension = parseExtension(fileToProc);
            if(extension.equals("hf")){
                try {
                    huffmanDecoder = new HuffmanDecoder(fileToProc);
                    logger.info("Starting decoding file: "+fileToProc.getFileName());
                    huffmanDecoder.decode();
                } catch (IOException e) {
                    logger.error("Exception in HuffmanEncoder.Encode: "+e.getMessage());
                }
            }
            else {
                try {
                    huffmanEncoder = new HuffmanEncoder(fileToProc);
                    logger.info("Starting coding file: "+fileToProc.getFileName());
                    huffmanEncoder.encode();
                } catch (IOException e) {
                    logger.error("Exception in HuffmanEncoder.Encode: "+e.getMessage());
                }
            }

        }
    }

    public static String parseExtension(Path path){
        String[] splitedFileName = path.getFileName().toString().split("\\.");
        return splitedFileName[splitedFileName.length-1];
    }
}
