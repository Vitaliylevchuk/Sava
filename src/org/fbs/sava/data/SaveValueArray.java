package org.fbs.sava.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SaveValueArray<T> extends SaveData {


    public SaveValueArray(String name, int id, List<T> elements) {
        super(id, name, SaveType.ARRAY);
        array.addAll(elements);
    }

    private final ArrayList<Object> array = new ArrayList<>();

    @Override
    public List<Object> getValues(){
        return Collections.unmodifiableList(array);
    }

}
