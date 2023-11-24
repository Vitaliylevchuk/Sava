# Sava

[![MIT License](https://img.shields.io/badge/License-MIT-blue.svg)](https://choosealicense.com/licenses/mit/)

Sava is a Java library for processing and saving the necessary data. With Sava, you can convert your save files in Classes and work with it.

## Documentation 

### Data types

| Sava typing | Java typing |
|-------------|-------------|
| long        | long        |
| double      | double      |
| int         | int         |
| str         | String      |
| char        | char        |
| bool        | boolean     |

### Value types

* **value**
> Can store values of the same data type, can store both primitives and values of a custom data type and called with `value`.
-------------
* **array**
> Can store values of the same data type, You can access an element using: name, id or index, it can be called by `array`.
-------------

### Starting

* If you want to compile folder with files:
> Implement the [CompileController](src/org/fbs/sava/controller/CompileController.java) class by passing it a folder with save files (files with the .sava extension), if there are none there, set the `hasFiles` parameter to _false_. After use method `getCompiledSaves()` to get array with [SaveFile](src/org/fbs/sava/data/SaveFile.java) objects.
-------------
* If you want to compile only one file:
> Implement the [Compiler](src/org/fbs/sava/util/Compiler.java) class and transfer the save file(file with the .sava extension), after use method `getCompiledSave()` to get [SaveFile](src/org/fbs/sava/data/SaveFile.java) object.

## Download

You can download Sava here _see later_.

## Thanks

* [Intellij Idea - java/kotlin IDE](https://www.jetbrains.com/idea/)
* [JetBrains annotations](https://github.com/JetBrains/java-annotations)
