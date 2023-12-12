package org.fbs.sava.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("all")
public class SaveValueArray<T> extends SaveData {


    public SaveValueArray(String name, int id, List<T> elements, Class<?> valueClass) {
        super(id, name, SaveType.ARRAY, valueClass);
        array.addAll(elements);
    }

    private final ArrayList<Object> array = new ArrayList<>();
    @Override
    public List<Object> getValues(){
        return Collections.unmodifiableList(array);
    }

    @Override
    public String toString(){
        String string = "";
        if (getValueClass().equals(Integer.class)) {
            string += "Integer ";
        } else if (getValueClass().equals(Double.class)) {
            string += "Double ";
        } else if (getValueClass().equals(Long.class)) {
            string += "Long ";
        }else if (getValueClass().equals(Character.class)) {
            string += "Character ";
        } else if (getValueClass().equals(String.class)) {
            string += "String ";
        } else if (getValueClass().equals(Boolean.class)) {
            string += "Boolean ";
        }
        string += ("| name: " + getName() + " id: " + getId() + " array:" + array.toString());

        return string;
    }
}
