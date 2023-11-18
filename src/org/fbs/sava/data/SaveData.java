package org.fbs.sava.data;

import java.util.ArrayList;

public class SaveData {

    public SaveData(int id, String name){
        this.id = id;
        this.name = name;
    }

    private final int id;
    private final String name;

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

class SaveValueArray <T> extends SaveValue<T>{

  @SafeVarargs
  public SaveValueArray(String name, int id, T ... elements){
      super(elements[0], 0, "");
      for (int i = 0; i < elements.length; i++) {
          array.add(i, new SaveValue<T>(elements[i], i, "" + elements[i] + "" + i));
      }
  }
  
  private final ArrayList<SaveValue<T>> array = new ArrayList<>();
  
  public SaveValue<T> getElementById(int id){
    return array.get(id);
  }
  
}