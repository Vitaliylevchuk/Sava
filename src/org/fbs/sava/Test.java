package org.fbs.sava;

import org.fbs.sava.data.SaveData;
import org.fbs.sava.data.SaveFile;
import org.fbs.sava.util.Compiler;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        Compiler compiler = new Compiler(new File("/home/vitaliy/IdeaProjects/Sava/test.sava"));
        SaveFile saveFile = compiler.getCompiledSave();


        for (SaveData data : saveFile.getAll()){
            System.out.println(data.getValue() + " " + data.getName() + " " + data.getId());
        }
        System.out.println(saveFile.getByName("a").size());
    }
}