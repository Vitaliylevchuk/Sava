package org.fbs.sava.data;

import java.util.ArrayList;
import java.util.Arrays;

public class SaveStructure {

    public SaveStructure (CustomSaveStructure[] customSaveStructures, SaveFile saveFile){
        customStructures.toArray(customSaveStructures);
        this.saveFile = saveFile;
    }
    private final ArrayList<CustomSaveStructure> customStructures = new ArrayList<>();
    private final SaveFile saveFile;
    private static final String[] dataType = {"long", "double", "int", "str", "char", "bool"};
    private static final String[] valueType = {"val", "array"};

    public int getCustomStructuresLength(){
        return customStructures.size();
    }
    public void addCustomSaveStructures(CustomSaveStructure structure){
        customStructures.add(structure);
    }
    public boolean isReservedWord(String word, boolean useCustomStructures){
        if (Arrays.asList(dataType).contains(word)){
            return true;
        }
        else if (Arrays.asList(valueType).contains(word)) {
            return true;
        }
        else if (useCustomStructures) {
            for(CustomSaveStructure structure : customStructures){
                if (Arrays.asList(structure.content()).contains(word)){
                    return true;
                }
            }
        }
        return false;
    }
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
    public static boolean isReservedWord(String word, SaveFile saveFile){
        if (Arrays.asList(dataType).contains(word)){
            return true;
        }
        else return Arrays.asList(valueType).contains(word);
    }

}