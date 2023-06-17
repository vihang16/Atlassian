package main.java;

import java.util.Objects;

public class FileObject {
    String name;
    String collectionName;
    public int size;

    public FileObject(String name, String collectionName, int size) {
        this.name = name;
        this.collectionName = collectionName;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileObject that = (FileObject) o;
        return collectionName.equals(that.collectionName);
    }

    @Override
    public String toString() {
        return "main.java.FileObject{" +
                "name='" + name + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionName);
    }
}
