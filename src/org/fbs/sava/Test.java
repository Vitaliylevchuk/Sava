package org.fbs.sava;

import org.fbs.sava.util.SaveFileReader;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        SaveFileReader reader = new SaveFileReader(new File("/home/vitaliy/IdeaProjects/Sava/test.txt"));
        System.out.println(reader.getFileText());
    }

}
