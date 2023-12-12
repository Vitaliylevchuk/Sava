package org.fbs.sava.data;

@SuppressWarnings("all")
public class SaveValue<T> extends SaveData {

    public SaveValue(T value, int id, String name, Class<?> valueClass) {
        //If it strings, you must type in "", if char you must type in ''.
        super(id, name, SaveType.VALUE, valueClass);
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

    @Override
    public String toString() {
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
        string += ("| name: " + getName() + " id: " + getId() + " value: " + value.toString());

        return string;
    }
}
