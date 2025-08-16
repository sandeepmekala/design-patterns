package com.design.pattern.behavioural;

import java.util.ArrayList;
import java.util.List;

// Memento
class EditorState {
    private final String state;

    public EditorState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

// Originator
class Editor {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public EditorState createState() {
        return new EditorState(state);
    }

    public void restore(EditorState memento) {
        this.state = memento.getState();
    }
}

// Caretaker Class
class History {
    private final List<EditorState> mementoList = new ArrayList<>();

    public void add(EditorState memento) {
        mementoList.add(memento);
    }

    public EditorState undo() {
        if (!mementoList.isEmpty()) {
            return mementoList.remove(mementoList.size() - 1);
        }
        return null;
    }
}

// Client Code
public class MementoClinet {
    public static void main(String[] args) {
        Editor editor = new Editor();
        History history = new History();

        // Initial State
        editor.setState("a");
        history.add(editor.createState());

        // Change State
        editor.setState("c");

        // Undo
        editor.restore(history.undo());
        System.out.println("After Undo: " + editor.getState());
    }
}

