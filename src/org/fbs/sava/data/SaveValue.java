package org.fbs.sava.data;

public class SaveValue<T> extends SaveData {

    public SaveValue(T value, int id, String name) {
        //If it strings, you must type in "", if char you must type in ''.
        super(id, name, SaveType.VALUE);
        this.value = value;
    }

    private final T value;
    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Class<?> getValueClass(){
        return value.getClass();
    }
}
