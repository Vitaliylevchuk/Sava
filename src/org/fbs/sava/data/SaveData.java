package org.fbs.sava.data;

public class SaveData {

    public SaveData(int id, String name){
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class SaveValue <T> extends SaveData{

    public SaveValue(T value, int id, String name){
        super(id, name);
        this.value = value;
    }

    private final T value;

    public T getValue() {
        return value;
    }
}

