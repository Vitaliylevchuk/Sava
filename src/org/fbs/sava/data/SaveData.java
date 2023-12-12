package org.fbs.sava.data;

import java.util.List;

public class SaveData {

    public SaveData(int id, String name, SaveType type, Class<?> valueClass){
        this.id = id;
        this.name = name;
        this.type = type;
        this.valueClass = valueClass;
    }

    private final int id;
    private final Class<?> valueClass;
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
        return valueClass;
    }
    public Object getValue() {
        return null;
    }
    @Deprecated
    public Class<?>[] getValueClasses(){
        // CREATE: 11.12.23: create a struct with every element classification
        return null;
    }
    public List<Object> getValues() {
        return null;
    }
}

