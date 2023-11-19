package org.fbs.sava.data;

import java.util.ArrayList;

public class SaveFile {

    public SaveFile(ArrayList<SaveData> contents){
        fileData = contents;
    }

    private final ArrayList <SaveData> fileData;

    public SaveData getByIndex(int index){
        return fileData.get(index);
    }

    public ArrayList <SaveData> getByType(ValueType type){

        ArrayList <SaveData> saveData = new ArrayList<>();

        for (int i = 0; i < fileData.size(); i++) {
            if (fileData.get(i).getValueType() == type){
                saveData.add(fileData.get(i));
            }
        }
        return saveData;
    }

    public ArrayList <SaveData> getByName(String name){

        ArrayList <SaveData> saveData = new ArrayList<>();

        for (int i = 0; i < fileData.size(); i++) {
            if (fileData.get(i).getName() == name){
                saveData.add(fileData.get(i));
            }
        }
        return saveData;

    }

    public ArrayList <SaveData> getById(int id){

        ArrayList <SaveData> saveData = new ArrayList<>();

        for (int i = 0; i < fileData.size(); i++) {
            if (fileData.get(i).getId() == id){
                saveData.add(fileData.get(i));
            }
        }
        return saveData;

    }

}
