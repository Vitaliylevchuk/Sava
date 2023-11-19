package org.fbs.sava.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SaveFileReader {

    private final File file;

    public String getFileText() {
        return fileText;
    }

    private String fileText = "";

    public SaveFileReader(File file) throws IOException {
        this.file = file;
        read();
    }

    private void read() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        int targetSymbol = reader.read();
        ArrayList<Character> line = new ArrayList<>();

        while (targetSymbol != -1){
            if (targetSymbol == 10){
                StringBuilder stringBuilder = new StringBuilder();
                for (Character ch : line) {
                    stringBuilder.append(ch);
                }
                fileText += stringBuilder + "\n";
                line.clear();
            }
            else {
                line.add((char) targetSymbol);
            }
            targetSymbol = reader.read();
        }

    }

}
