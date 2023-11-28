package org.fbs.sava.data;

public class SaveValue<T> extends SaveData {

    public SaveValue(T value, int id, String name) {
        //If it strings, you must type in "", if char you must type in ''.
        super(id, name);
        this.value = value;
    }

    private final T value;
    public T getValue() {
        return value;
    }
}
