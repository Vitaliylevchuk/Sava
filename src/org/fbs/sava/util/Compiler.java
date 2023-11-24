package org.fbs.sava.util;

import org.fbs.sava.data.CustomSaveStructure;
import org.fbs.sava.data.SaveFile;
import org.fbs.sava.data.SaveStructure;
import org.fbs.sava.exception.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Compiler {

    private final File file;
    private ArrayList<String> textFile = new ArrayList<>();
    private SaveStructure saveStructure;
    private SaveFile saveFile;
    public SaveFile getSaveFile() {
        return saveFile;
    }
    public ArrayList<String> getTextFile() {
        return textFile;
    }

    public Compiler(File file, CustomSaveStructure[] customStructures) throws IOException, SaveFileException {
        if (file.isDirectory()){
            throw new InputParameterException(file.getAbsolutePath() + " is directory, must be file.");
        }
        saveStructure = new SaveStructure(customStructures, saveFile);
        this.file = file;
        compile();
    }

    public SaveFile getCompiledSave(){
        return saveFile;
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
                        boolean useCustomStructures = saveStructure.getCustomStructuresLength() > 0;
                        if (saveStructure.isReservedWord(word, useCustomStructures)){
                            // TODO: 24.11.2023  
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
