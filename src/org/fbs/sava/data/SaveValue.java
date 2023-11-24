package org.fbs.sava.data;

public class SaveValue<T> extends SaveData {

    public SaveValue(T value, int id, String name) {
        //If it strings, you must type in "", if char you must type in ''.
        super(id, name, ValueType.VALUE, "val <data_type> <name> = <value>");
        this.value = value;
    }

    private final ValueType type = ValueType.VALUE;
    private final T value;

    public T getValue() {
        return value;
    }
}
