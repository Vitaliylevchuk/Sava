package org.fbs.sava;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface SaveFile {

    default void setFile(File file, @NotNull Compiler compiler) {
        compiler.setSaveFile(file);
    }

}
