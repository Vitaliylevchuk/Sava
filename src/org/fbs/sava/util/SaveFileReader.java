package org.fbs.sava.util;

import java.io.*;
import java.util.ArrayList;

public class SaveFileReader {

    private final File file;

    public String getFileText() {
        return fileText;
    }
    public ArrayList<String> getTextLines(){
        return textLines;
    }

    private String fileText = "";
    private ArrayList<String> textLines = new ArrayList<>();

    public SaveFileReader(File file) throws IOException {
        this.file = file;
        read();
    }

    private void read() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        int targetSymbol = reader.read();
        int nextLineCombo = 0;
        ArrayList<Character> line = new ArrayList<>();

        while (targetSymbol != -1){
            if (targetSymbol == 10 && nextLineCombo == 0){
                StringBuilder stringBuilder = new StringBuilder();
                for (Character ch : line) {
                    stringBuilder.append(ch);
                }
                String targetLine = stringBuilder.toString();
                if (!targetLine.isBlank()){
                    fileText += targetLine.trim() + "\n";
                    textLines.add(targetLine.trim());
                    nextLineCombo++;
                }
                line.clear();
            }
            else if (targetSymbol != 10){
                line.add((char) targetSymbol);
                nextLineCombo = 0;
            }
            targetSymbol = reader.read();
        }
        if(targetSymbol == -1){
            StringBuilder stringBuilder = new StringBuilder();
            for (Character ch : line) {
                stringBuilder.append(ch);
            }
            String targetLine = stringBuilder.toString();
            if (!targetLine.isBlank()){
                fileText += targetLine.trim() + "\n";
                textLines.add(targetLine.trim());
                nextLineCombo++;
            }
            line.clear();
        }

    }

}
