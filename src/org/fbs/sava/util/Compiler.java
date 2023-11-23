package org.fbs.sava.util;

import org.fbs.sava.data.SaveFile;
import org.fbs.sava.data.SaveStructure;
import org.fbs.sava.exception.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Compiler {

    private final File file;
    private ArrayList<String> textFile = new ArrayList<>();
    private SaveFile saveFile;
    public SaveFile getSaveFile() {
        return saveFile;
    }
    public ArrayList<String> getTextFile() {
        return textFile;
    }

    public Compiler(File file) throws IOException, SaveFileException {

        this.file = file;
        compile();
    }

    public void compile() throws IOException, EmptyFileException, OperatorException {
        SaveFileReader saveFileReader = new SaveFileReader(file);
        textFile = saveFileReader.getTextLines();

        if (!textFile.isEmpty()){
            for (String str: textFile) {

                if(str.indexOf(';') == -1 || !(str.lastIndexOf(';') == str.toCharArray().length-1)){
                    throw new OperatorException("Line ending operator ';' not found. Line: \"" + str + "\".");
                }

                for (String line: str.split(";")) {

                    String [] words = line.split(" ");
                    for (String word: words) {
                        if (new SaveStructure(saveFile).isReservedWord(word)){
                            // TODO: 21.11.2023
                        }
                    }
                }
            }
        }
        else {
            throw new EmptyFileException(file.getName());
        }

    }

}
