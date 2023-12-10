package org.fbs.sava;

import org.fbs.sava.data.SaveFile;
import org.fbs.sava.util.Compiler;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        Compiler compiler = new Compiler(new File("/home/vitaliy/IdeaProjects/Sava/test.sava"));
        SaveFile saveFile = compiler.getCompiledSave();
        

        System.out.println(saveFile.getAll().get(2).getName());

        for(Object object: saveFile.getAll().get(2).getValues()) {
            System.out.println(object);
        }
    }
}