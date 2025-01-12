package com.design.pattern.structural;

import java.util.ArrayList;
import java.util.List;

// Component
interface FileSystem {
    void ls();
}

// Leaf
class File implements FileSystem {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void ls() {
        System.out.println("File: " + name);
    }
}

// Composite
class Directory implements FileSystem {
    private String name;
    private List<FileSystem> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystem component) {
        children.add(component);
    }

    public void remove(FileSystem component) {
        children.remove(component);
    }

    @Override
    public void ls() {
        System.out.println("Directory: " + name);
        for (FileSystem child : children) {
            child.ls();
        }
    }
}

// Client
public class CompositeClient {
    public static void main(String[] args) {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        Directory directory = new Directory("Documents");

        directory.add(file1);
        directory.add(file2);

        Directory root = new Directory("Root");
        root.add(directory);
        root.ls();
    }
}
