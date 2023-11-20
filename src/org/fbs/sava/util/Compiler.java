package org.fbs.sava.util;

import java.io.File;
import java.io.IOException;

public class Compiler {

    private final File saveFile;
    private String textFile;

    public Compiler(File saveFile) throws IOException {
        this.saveFile = saveFile;
        compile();
    }

    private void compile() throws IOException {
        SaveFileReader saveFileReader = new SaveFileReader(saveFile);
        textFile = saveFileReader.getFileText();
    }



}
