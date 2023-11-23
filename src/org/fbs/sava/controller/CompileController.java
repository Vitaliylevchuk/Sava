package org.fbs.sava.controller;

import org.fbs.sava.exception.InputParameterException;
import org.fbs.sava.exception.SaveFileException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class CompileController {

    private final boolean hasFiles;
    public ArrayList<File> files = new ArrayList<>();

    public CompileController(@NotNull File savePackage, boolean hasFiles) throws SaveFileException, IOException {
        this.hasFiles = hasFiles;
        if (savePackage.isFile()){
            throw new InputParameterException(savePackage.getAbsolutePath() + " is file, must be directory.");
        }

        if (hasFiles){
            String extension = "";
            for (File file : Objects.requireNonNull(savePackage.listFiles())) {
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
        }
        if (files.isEmpty()){
            throw new InputParameterException("Package " + savePackage.getAbsolutePath() + " does not have .sava files.");
        }
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
