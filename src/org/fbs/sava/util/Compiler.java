package org.fbs.sava.util;

import org.fbs.sava.data.*;
import org.fbs.sava.exception.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Compiler {

    private final File file;
    private ArrayList<String> textFile = new ArrayList<>();
    private final SaveStructure saveStructure;
    private SaveFile saveFile = new SaveFile();
    public ArrayList<String> getTextFile() {
        return textFile;
    }

    public Compiler(@NotNull File file) throws IOException{
        if (file.isDirectory()){
            throw new ParameterException(file.getAbsolutePath() + " is directory, must be file.");
        }
        saveStructure = new SaveStructure(saveFile);
        this.file = file;
        compile();
    }

    public SaveFile getCompiledSave(){
        return saveFile;
    }

    public void compile() throws IOException{
        saveFile = new SaveFile();
        SaveFileReader saveFileReader = new SaveFileReader(file);
        textFile = saveFileReader.getTextLines();

        if (!textFile.isEmpty()){
            for (String str: textFile) {

                if (str.contains("//")){
                    continue;
                }
                if(str.indexOf(';') == -1 || !(str.lastIndexOf(';') == str.toCharArray().length-1)){
                    throw new OperatorException("Line ending operator ';' not found. Line: \"" + str + "\".");
                }

                for (String line: str.split(";")) {

                    System.out.println(line);

                    if (line.toCharArray().length == 0){
                        throw new StandardSyntaxException("Empty line " + str);
                    }

                    String [] words = line.split(" ");
                    boolean isOnCreatingStruct = false;
                    boolean[] selectedStruct = {false, false};
                    int phase = 1;
                    boolean readStr = false;
                    String string = "";

                    List<Object> list = new ArrayList<>();

                    String type = "";
                    int id = 0;
                    String name = "";

                    for (String word: words) {
                        if (isOnCreatingStruct){
                            if (selectedStruct[0]){
                                switch (phase){
                                    case 1: {
                                        name = word;
                                        phase ++;
                                        continue;
                                    }
                                    case 2:{

                                        type = word;
                                        if (!Arrays.asList(SaveStructure.dataType).contains(word)){
                                            throw new StandardSyntaxException("Unknown data type: " + word);
                                        }
                                        phase ++;
                                        continue;
                                    }
                                    case 3:{
                                        if (Objects.equals(word, "=")){
                                            continue;
                                        }
                                        else {
                                            id = (name + type).hashCode();
                                            if (Arrays.asList(new String[]{"long", "double", "int"}).contains(type)){
                                                StringBuilder newWord = new StringBuilder();
                                                for (char ch : word.toCharArray()){
                                                    if (Arrays.asList(new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'}).contains(ch)){
                                                        newWord.append(ch);
                                                    }
                                                }
                                                word = newWord.toString();
                                            }
                                            else if (Objects.equals(type, "bool")){
                                                if (word.contains("true") || word.contains("false")){
                                                    if (word.contains("true")){
                                                        word = "true";
                                                    }
                                                    else{
                                                        word = "false";
                                                    }
                                                }
                                            }
                                            System.out.println(word);
                                            switch (type){
                                                case "long":{
                                                    try {
                                                        saveFile.addData(new SaveValue<Long>(Long.valueOf(word), id, name));
                                                        type = "";
                                                        isOnCreatingStruct = false;
                                                        selectedStruct[0] = false;
                                                        phase = 1;
                                                        break;
                                                    }
                                                    catch (ClassCastException e){
                                                        throw new StandardSyntaxException("Incorrect value(s): " + list + " select right data type.");
                                                    }
                                                }
                                                case "int":{
                                                    try {
                                                        saveFile.addData(new SaveValue<Integer>(Integer.valueOf(word), id, name));
                                                        type = "";
                                                        isOnCreatingStruct = false;
                                                        selectedStruct[0] = false;
                                                        phase = 1;
                                                        break;
                                                    }
                                                    catch (ClassCastException e){
                                                        throw new StandardSyntaxException("Incorrect value(s): " + list + " select right data type.");
                                                    }
                                                }
                                                case "double":{
                                                    try {
                                                        saveFile.addData(new SaveValue<Double>(Double.valueOf(word), id, name));
                                                        type = "";
                                                        isOnCreatingStruct = false;
                                                        selectedStruct[0] = false;
                                                        phase = 1;
                                                        break;
                                                    }
                                                    catch (ClassCastException e){
                                                        throw new StandardSyntaxException("Incorrect value(s): " + list + " select right data type.");
                                                    }
                                                }
                                                case "bool":{
                                                    try {
                                                        saveFile.addData(new SaveValue<Boolean>(Boolean.valueOf(word), id, name));
                                                        type = "";
                                                        isOnCreatingStruct = false;
                                                        selectedStruct[0] = false;
                                                        phase = 1;
                                                        break;
                                                    }
                                                    catch (ClassCastException e){
                                                        throw new StandardSyntaxException("Incorrect value(s): " + list + " select right data type.");
                                                    }
                                                }
                                                case "str":{
                                                    if (word.indexOf("\"") != word.lastIndexOf("\"") && word.contains("\"") && word.toCharArray()[word.toCharArray().length-1] == '\"'){
                                                        word = word.replaceAll("\"", "");
                                                        word = word.trim();
                                                        saveFile.addData(new SaveValue<String>(word, id, name));
                                                        type = "";
                                                        isOnCreatingStruct = false;
                                                        selectedStruct[0] = false;
                                                        phase = 1;
                                                    }
                                                    else if (line.contains("\"") && (line.indexOf("\"") != list.lastIndexOf("\""))){
                                                        saveFile.addData(new SaveValue<String>(getBetween(line, '\"'), id, name));
                                                        type = "";
                                                        isOnCreatingStruct = false;
                                                        selectedStruct[0] = false;
                                                        phase = 1;
                                                    }
                                                    else {
                                                        throw new StandardSyntaxException("\" expected.");
                                                    }

                                                    break;
                                                }
                                                case "char":{
                                                    try {
                                                        saveFile.addData(new SaveValue<Character>((word).replaceAll("'", "").toCharArray()[0], id, name));
                                                        type = "";
                                                        isOnCreatingStruct = false;
                                                        selectedStruct[0] = false;
                                                        phase = 1;
                                                        break;
                                                    }
                                                    catch (ClassCastException e){
                                                        throw new StandardSyntaxException("Incorrect value(s): " + list + " select right data type.");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else if (selectedStruct[1]){
                                switch (phase){
                                    case 1: {
                                        name = word;
                                        phase ++;
                                        continue;
                                    }
                                    case 2:{
                                        type = word;
                                        if (!Arrays.asList(SaveStructure.dataType).contains(word)){
                                            throw new StandardSyntaxException("Unknown data type: " + word);
                                        }
                                        phase ++;
                                        continue;
                                    }
                                    case 3: {
                                        if ((word.contains("{") && word.toCharArray().length == 1)||(word.contains(",") && word.toCharArray().length == 1)) {
                                            continue;
                                        }
                                        if ((word.contains("{") && word.toCharArray().length != 1)||(word.contains(",") && word.toCharArray().length != 1)){
                                            word = word.replace("{", "");
                                        }
                                        else {
                                            word = word.replaceAll(",", "");
                                            id = (name + type).hashCode();
                                            boolean isEnd = false;
                                            if (word.contains("}")){
                                                word = word.replace("}", "");
                                                isEnd = true;
                                            }
                                            switch (type){
                                                case "int":{
                                                    list.add(Integer.valueOf(word));
                                                    if (isEnd){
                                                        phase = 4;
                                                    }
                                                    continue;
                                                }
                                                case "double":{
                                                    list.add(Double.valueOf(word));
                                                    if (isEnd){
                                                        phase = 4;
                                                    }
                                                    continue;
                                                }
                                                case "char":{
                                                    list.add((word).replaceAll("'", "").toCharArray()[0]);
                                                    if (isEnd){
                                                        phase = 4;
                                                    }
                                                    continue;
                                                }
                                                case "bool":{
                                                    list.add(Boolean.valueOf(word));
                                                    if (isEnd){
                                                        phase = 4;
                                                    }
                                                    continue;
                                                }
                                                case "long":{
                                                    list.add(Long.valueOf(word));
                                                    if (isEnd){
                                                        phase = 4;
                                                    }
                                                    continue;
                                                }
                                                case "str":{
                                                    if (word.indexOf("\"") != word.lastIndexOf("\"") && word.contains("\"") && word.toCharArray()[word.toCharArray().length-1] == '\"'){
                                                        string += word.replaceAll("\"", "");
                                                        list.add(string);
                                                    }
                                                    else{
                                                        String arrayData = getBetween(line, '{', '}');
                                                        String[] arrayContents = arrayData.split(",");
                                                        for (String content: arrayContents){
                                                            if (content.indexOf("\"") != content.lastIndexOf("\"") && content.contains("\"")){
                                                                string += getBetween(content.trim(), '"').replaceAll("\"", "");
                                                                list.add(string);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    case 4:{
                                        switch (type){
                                            case "int":{
                                                try {
                                                    saveFile.addData(new SaveValueArray<Integer>(name, id, (Integer[]) list.toArray()));
                                                    type = "";
                                                    isOnCreatingStruct = false;
                                                    selectedStruct[1] = false;
                                                    phase = 1;
                                                    break;
                                                }
                                                catch (ClassCastException e){
                                                    throw new StandardSyntaxException("Incorrect value(s): " + list + " select right data type.");
                                                }
                                            }
                                            case "double":{
                                                try {
                                                    saveFile.addData(new SaveValueArray<Double>(name, id, (Double[]) list.toArray()));
                                                    type = "";
                                                    isOnCreatingStruct = false;
                                                    selectedStruct[1] = false;
                                                    phase = 1;
                                                    break;
                                                }
                                                catch (ClassCastException e){
                                                    throw new StandardSyntaxException("Incorrect value(s): " + list + " select right data type.");
                                                }
                                            }
                                            case "long":{
                                                try {
                                                    saveFile.addData(new SaveValueArray<Long>(name, id, (Long[]) list.toArray()));
                                                    type = "";
                                                    isOnCreatingStruct = false;
                                                    selectedStruct[1] = false;
                                                    phase = 1;
                                                    break;
                                                }
                                                catch (ClassCastException e){
                                                    throw new StandardSyntaxException("Incorrect value(s): " + list + " select right data type.");
                                                }
                                            }
                                            case "char":{
                                                saveFile.addData(new SaveValueArray<Character>(name, id, (Character[]) list.toArray()));
                                                type = "";
                                                isOnCreatingStruct = false;
                                                selectedStruct[1] = false;
                                                phase = 1;
                                                break;
                                            }
                                            case "str":{
                                                saveFile.addData(new SaveValueArray<String>(name, id, (String[]) list.toArray()));
                                                type = "";
                                                isOnCreatingStruct = false;
                                                selectedStruct[1] = false;
                                                phase = 1;
                                                break;
                                            }
                                            case "bool":{
                                                try {
                                                    saveFile.addData(new SaveValueArray<Boolean>(name, id, (Boolean[]) list.toArray()));
                                                    type = "";
                                                    isOnCreatingStruct = false;
                                                    selectedStruct[1] = false;
                                                    phase = 1;
                                                    break;
                                                }
                                                catch (ClassCastException e){
                                                    throw new StandardSyntaxException("Incorrect value(s): " + list + " select right data type.");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else if (saveStructure.isReservedWord(word)){
                            isOnCreatingStruct = true;
                            switch (word){
                                case "val":{
                                    selectedStruct[0] = true;
                                    continue;
                                }
                                case "array":{
                                    selectedStruct[1] = true;
                                    continue;
                                }
                                default:{
                                    throw new StandardSyntaxException("Unknown structure " + word);
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            throw new EmptyFileException(file.getName());
        }

        if (textFile.size() != saveFile.getAll().size()){
            System.out.println("\u001B[33m" + "The number of structures does not correspond to the number of values, perhaps you made a mistake." + "\u001B[0m");
        }
        saveFile.setFinale();

    }

    public String getBetween(@NotNull String string, char symbol){
        StringBuilder str = new StringBuilder();
        boolean reading = false;
        for (char ch: string.toCharArray()) {
            if (ch == symbol){
                if (reading){
                    break;
                }
                reading = true;
                continue;
            }
            if (reading){
                str.append(ch);
            }
        }
        return str.toString();
    }
    public String getBetween(@NotNull String string, char symbolStart, char symbolStop){
        StringBuilder str = new StringBuilder();
        boolean reading = false;
        for (char ch: string.toCharArray()) {
            if (ch == symbolStart){
                reading = true;
                continue;
            }
            else if (ch == symbolStop){
                break;
            }
            if (reading){
                str.append(ch);
            }
        }
        return str.toString();
    }

}
