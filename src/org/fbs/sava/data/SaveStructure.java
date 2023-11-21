package org.fbs.sava.data;

import java.util.Arrays;

public class SaveStructure {

    private final SaveFile saveFile;
    public SaveStructure(SaveFile saveFile){
        this.saveFile = saveFile;
    }

    private static final String[] dataType = {"long", "double", "int", "str", "char", "bool"};
    private static final String[] valueType = {"val", "array"};

    public boolean isReservedWord(String word){
        if (Arrays.asList(dataType).contains(word)){
            return true;
        }
        else if (Arrays.asList(valueType).contains(word)) {
            return true;
        }
        else{
            for (SaveData data : saveFile.getByType(ValueType.All)) {
                if (data.getName() == word){
                    return true;
                }
            }

        }
        return false;
    }

}
