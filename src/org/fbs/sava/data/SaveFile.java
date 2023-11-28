package org.fbs.sava.data;

import java.util.ArrayList;
import java.util.Objects;

public class SaveFile {

    public SaveFile(){}
    public SaveFile(ArrayList<SaveData> contents){
        fileData = contents;
    }

    private ArrayList <SaveData> fileData = new ArrayList<>();

    public void addData(SaveData data){
        fileData.add(data);
    }
    public SaveData getByIndex(int index){
        return fileData.get(index);
    }

    public ArrayList <SaveData> getAll(){
        try {
            return fileData;
        }
        catch (NullPointerException e){
            return null;
        }
    }

    public ArrayList <SaveData> getByName(String name){

        ArrayList <SaveData> saveData = new ArrayList<>();

        for (SaveData fileDatum : fileData) {
            if (Objects.equals(fileDatum.getName(), name)) {
                saveData.add(fileDatum);
            }
        }
        return saveData;

    }

    public ArrayList <SaveData> getById(int id){

        ArrayList <SaveData> saveData = new ArrayList<>();

        for (SaveData fileDatum : fileData) {
            if (fileDatum.getId() == id) {
                saveData.add(fileDatum);
            }
        }
        return saveData;

    }

}
