package com.module.huffman;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {
    public static void main(String[] args) {
        Path fileToProc;
        if (args.length==0) {
            System.exit(-500);
        } else {
            fileToProc = Paths.get(args[0]);
            System.out.println();
            String extension = parseExtension(fileToProc);
            if(extension.equals("hf")){

            }
            else {

            }

        }
    }

    public static String parseExtension(Path path){
        String[] splitedFileName = path.getFileName().toString().split("\\.");
        return splitedFileName[splitedFileName.length-1];
    }
}
