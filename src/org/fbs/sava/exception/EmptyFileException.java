package org.fbs.sava.exception;

public class EmptyFileException extends SaveFileException{

    public EmptyFileException(String fileName){
        super("File " + fileName + " is empty.");
    }

}
