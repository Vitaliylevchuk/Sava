package org.fbs.sava.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Compiler {

    private final File saveFile;
    private ArrayList<String> textFile = new ArrayList<>();

    public Compiler(File saveFile) throws IOException {
        this.saveFile = saveFile;
        compile();
    }

    private void compile() throws IOException {
        SaveFileReader saveFileReader = new SaveFileReader(saveFile);
        textFile = saveFileReader.getTextLines();

        

    }

}
