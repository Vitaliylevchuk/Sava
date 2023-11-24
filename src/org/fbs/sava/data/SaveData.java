package org.fbs.sava.data;

public class SaveData {

    public SaveData(int id, String name, ValueType valueType, String syntax){
        this.id = id;
        this.name = name;
        this.valueType = valueType;
        this.syntax = syntax;
    }

    private final String syntax;
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
    public String getSyntax() {
        return syntax;
    }
}

