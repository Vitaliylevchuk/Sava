package org.fbs.sava;

import org.fbs.sava.controller.CompileController;
import org.fbs.sava.exception.SaveFileException;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws SaveFileException, IOException {

        CompileController compileController = new CompileController(new File("/home/vitaliy/IdeaProjects/Sava/"), true);
        System.out.println(compileController.files.size());

    }

}
