package org.fbs.sava.data;

import java.util.ArrayList;

public class SaveData {

    public SaveData(int id, String name, ValueType valueType){
        this.id = id;
        this.name = name;
        this.valueType = valueType;
    }

    private final int id;
    private final ValueType valueType;
    private final String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public ValueType getValueType() {
        return valueType;
    }
}

class SaveValue <T> extends SaveData{

    public SaveValue(T value, int id, String name){
        super(id, name, ValueType.VALUE);
        this.value = value;
    }

    private final T value;

    public T getValue() {
        return value;
    }
}

class SaveValueArray <T> extends SaveData{

    @SafeVarargs
    public SaveValueArray(String name, int id, T ... elements){
        super(0, "", ValueType.ARRAY);
        for (int i = 0; i < elements.length; i++) {
            array.add(i, new SaveValue<T>(elements[i], i, "" + elements[i] + "" + i));
        }
    }
  
    private final ArrayList<SaveValue<T>> array = new ArrayList<>();
  
    public SaveValue<T> getElementById(int id){
    return array.get(id);
  }
  
}

enum ValueType{
    VALUE, ARRAY, All
}