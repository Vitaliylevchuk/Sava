package org.fbs.sava.data;

import java.util.List;

public class SaveData {

    public SaveData(int id, String name, SaveType type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    private final int id;
    private final SaveType type;
    private final String name;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public SaveType getType() {
        return type;
    }
    public Class<?> getValueClass(){
        return null;
    }
    public Object getValue() {
        return null;
    }
    public Class<?>[] getValueClasses(){
        return null;
    }
    public List<Object> getValues() {
        return null;
    }
}

