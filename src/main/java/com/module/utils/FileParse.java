package com.module.utils;

import java.nio.file.Path;

public class FileParse {

    public static String parseExtension(Path path) {
        String[] splitedFileName = path.getFileName().toString().split("\\.");
        return splitedFileName[splitedFileName.length - 1];
    }
}
