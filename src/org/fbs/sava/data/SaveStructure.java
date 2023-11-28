package org.fbs.sava.data;

import java.util.Arrays;

public class SaveStructure {

    public SaveStructure (SaveFile saveFile){
        this.saveFile = saveFile;
    }
    private final SaveFile saveFile;
    public static final String[] dataType = {"long", "double", "int", "str", "char", "bool"};
    public static final String[] standardStruct = {"val", "array"};

    public String wordMeaning(String word){
        if (Arrays.asList(dataType).contains(word)){
            return "data_type";
        }
        else if (Arrays.asList(standardStruct).contains(word)) {
            return "struct_start";
        }
        return "unknown";
    }

    public boolean isReservedWord(String word){
        if (Arrays.asList(dataType).contains(word)){
            return true;
        }
        else if (Arrays.asList(standardStruct).contains(word)) {
            return true;
        }
        else{
            for (SaveData data : saveFile.getAll()) {
                if (data.getName() == word){
                    return true;
                }
            }

        }
        return false;
    }

}