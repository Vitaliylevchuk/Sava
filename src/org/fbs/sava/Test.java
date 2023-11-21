package org.fbs.sava;

import org.fbs.sava.exception.SaveFileException;
import org.fbs.sava.util.Compiler;
import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, SaveFileException {
        Compiler compiler = new Compiler(new File("/home/vitaliy/IdeaProjects/Sava/test.txt"));

        for (String str : compiler.getTextFile()) {
            System.out.println(str);
        }
        
    }

}
