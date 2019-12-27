package com.module.files;

import com.module.utils.BitUtils;

import java.io.*;
import java.nio.file.Path;

public class BitFileReader {
    private InputStream input;
    private int buffer;
    private int position;


    public BitFileReader(Path pathToFile) throws FileNotFoundException {
        this.input = new BufferedInputStream(new FileInputStream(pathToFile.toFile()));
        this.buffer = 0;
        this.position = 7;
    }

    public int next(int count) throws IOException {
        if (count <= Integer.SIZE) {
            int result = 0;
            for (int i = 0; i < count; i++) {
                result = BitUtils.merge(result, (byte) next());
            }
            return result;
        }
        throw new IllegalArgumentException("Integer overflow on filling");
    }

    public int next() throws IOException {
        if (position != -1) {
            int result = BitUtils.getBit(buffer, position);
            position--;
            return result;
        } else {
            if (appendToBuffer() > 0) {
                position = 7;
                buffer = input.read();
                return next();
            } else
                return -1;
        }
    }

    private int appendToBuffer() throws IOException {
        if (input.available() > 0) {
            byte[] bytes = new byte[1];
            input.read(bytes);
            buffer = BitUtils.merge(bytes);
            return buffer;
        } else return -1;
    }
}
