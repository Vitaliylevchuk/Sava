package org.fbs.sava.data;

import java.util.ArrayList;

public class SaveValueArray<T> extends SaveData {

    @SafeVarargs
    public SaveValueArray(String name, int id, T... elements) {
        super(id, name);
        for (int i = 0; i < elements.length; i++) {
            array.add(i, new SaveValue<T>(elements[i], i, "" + elements[i] + "" + i));
        }
    }

    private final ArrayList<SaveValue<T>> array = new ArrayList<>();
    public SaveValue<T> getElementById(int id) {
        return array.get(id);
    }

}
