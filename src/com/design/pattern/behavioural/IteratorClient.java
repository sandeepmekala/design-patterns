package com.design.pattern.behavioural;

import java.util.*;
import java.util.Iterator;
 
// Iterator pattern provides you uniform way of accessing different collections of objects
// If you get array, arraylist and hashtable of objects, you can use iterator to uniformly access them
// You can also write polymorphic code because you can refer to each collection of objects because they implement the same interface

// Iterator interface
interface BookIterator {
    boolean hasNext();
    String next();
}

// ConcreteIterator
class BookListIterator implements BookIterator {
    private List<String> books;
    private int position;

    public BookListIterator(List<String> books) {
        this.books = books;
        this.position = 0;
    }

    public boolean hasNext() {
        return position < books.size();
    }

    public String next() {
        if (hasNext()) {
            return books.get(position++);
        }
        return null;
    }
}

// Aggregate interface
interface BookCollection {
    BookIterator createIterator();
}

// ConcreteAggregate
class Library implements BookCollection {
    private List<String> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(String book) {
        books.add(book);
    }

    public BookIterator createIterator() {
        return new BookListIterator(books);
    }
}

// Client
public class IteratorClient {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("Book 1");
        library.addBook("Book 2");
        library.addBook("Book 3");

        BookIterator iterator = library.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
