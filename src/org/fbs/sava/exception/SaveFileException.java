package org.fbs.sava.exception;

import java.io.IOException;

public class SaveFileException extends IOException {

    public SaveFileException(String errorMessage) {
        super(errorMessage);
    }
}
