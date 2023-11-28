package org.fbs.sava.controller;

import org.fbs.sava.data.SaveFile;
import org.fbs.sava.exception.ParameterException;
import org.fbs.sava.exception.SaveFileException;
import org.fbs.sava.util.Compiler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class CompileController {

    private final ArrayList<File> files = new ArrayList<>();
    private final Compiler[] compilers;

    public CompileController(@NotNull File savePackage, boolean hasFiles) throws IOException {
        if (savePackage.isFile()){
            throw new ParameterException(savePackage.getAbsolutePath() + " is file, must be directory.");
        }

        if (hasFiles){
            compilers = new Compiler[savePackage.listFiles().length];
            String extension = "";
            for (File file : Objects.requireNonNull(savePackage.listFiles())) {
                if (file.isDirectory()){
                    continue;
                }
                try {
                    extension = getExtension(file);
                } catch (SaveFileException ignored) {extension = "." + ((new Random()).nextInt(0, 99999999)); }
                if (Objects.equals(extension, ".sava")) {
                    files.add(file);
                }

            }
        }
        else {
            files.add(new File(savePackage.getAbsoluteFile() + "/save0.sava"));
            files.get(0).createNewFile();
            compilers = new Compiler[savePackage.listFiles().length];
        }
        if (files.isEmpty()){
            throw new ParameterException("Package " + savePackage.getAbsolutePath() + " does not have .sava files.");
        }
    }

    public SaveFile[] getCompiledSaves() throws IOException{
        SaveFile[] compiled = new SaveFile[files.size()];
        for (Compiler compiler: compilers){
            compiler.compile();
        }
        for (int i = 0; i < compiled.length; i++) {
            compiled[i] = compilers[i].getCompiledSave();
        }
        return compiled;
    }

    public String getExtension(File file) throws SaveFileException {
        if (file.getName().lastIndexOf(".") == -1){
            throw new SaveFileException("File does not have extension.");
        }
        String extension = "";
        for (int i = file.getName().lastIndexOf("."); i < file.getName().length(); i++) {
            extension += file.getName().toCharArray()[i];
        }
        return extension;
    }

    public static boolean createSaveFile(String name, @NotNull File path) throws IOException {
        return new File(path.getAbsoluteFile() + "/" + name + ".sava").createNewFile();
    }

}
