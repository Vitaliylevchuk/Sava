package org.fbs.sava;

import org.fbs.sava.util.Compiler;
import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        Compiler compiler = new Compiler(new File("/home/vitaliy/IdeaProjects/Sava/test.txt"));
    }

}
