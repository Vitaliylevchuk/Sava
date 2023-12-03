package org.fbs.sava.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SaveFile {

    public SaveFile(){}
    public SaveFile(ArrayList<SaveData> contents){
        fileData = contents;
    }
    private boolean finales = false;
    private ArrayList <SaveData> fileData = new ArrayList<>();
    public void addData(SaveData data){
        if (!finales) {
            fileData.add(data);
        }
    }
    public SaveData getByIndex(int index){
        return fileData.get(index);
    }
    public void setFinale(){
        finales = true;
    }

    public List<SaveData> getAll(){
        try {
            if (finales) {
                return Collections.unmodifiableList(fileData);
            }
            else {
                return fileData;
            }
        }
        catch (NullPointerException e){
            return null;
        }
    }

    public SaveData getByName(String name){

        for (SaveData fileDatum : fileData) {
            if (Objects.equals(fileDatum.getName(), name)) {
                return fileDatum;
            }
        }
        return null;

    }

    public SaveData getById(int id){

        for (SaveData fileDatum : fileData) {
            if (fileDatum.getId() == id) {
                return fileDatum;
            }
        }
        return null;

    }

    public List<SaveData> getByStructType(SaveType type){
        ArrayList<SaveData> data = new ArrayList<>();
        for (SaveData saveData: fileData){
            if (saveData.getType() == type){
                data.add(saveData);
            }
        }

        if(finales){
            return Collections.unmodifiableList(data);
        }
        else {
            return data;
        }

    }

}
