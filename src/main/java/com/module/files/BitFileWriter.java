package com.module.files;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class BitFileWriter {

    private int dwordVal = 0;
    private int bitsFree = Integer.SIZE;
    private BufferedOutputStream bufferedOutputStream;

    public BitFileWriter(Path pathToFile) throws FileNotFoundException {
        this.bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(pathToFile.toFile(),true));
    }

    public void appendHuffmanCodeToFile(int valueToAppend, int codeLength, int remainingLength) throws IOException {
        if ((bitsFree - remainingLength) >= 0) {
            //buffer can eat valueToAppend
            dwordVal = dwordVal | valueToAppend;
            bitsFree -= remainingLength;
            if (bitsFree == 0) {
                bufferedOutputStream.write(dwordVal);
                bitsFree = Integer.SIZE;
            } else
                dwordVal = dwordVal << bitsFree;
        } else {
            // buffer is overflowed
            int valThatCanBePlaced = valueToAppend >>> (codeLength - bitsFree);

            dwordVal = dwordVal | valThatCanBePlaced;

            valueToAppend = valueToAppend << (Integer.SIZE - codeLength + bitsFree);
            valueToAppend = valueToAppend >>> (Integer.SIZE - codeLength + bitsFree);
            remainingLength -= bitsFree;

            bufferedOutputStream.write(dwordVal);
            bitsFree = Integer.SIZE;
            if (remainingLength != 0)
                appendHuffmanCodeToFile(valueToAppend, codeLength, remainingLength);
        }
    }

    public void flushAndClose() throws IOException {
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
}
