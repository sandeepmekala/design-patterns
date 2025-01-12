package com.design.pattern.behavioural;

import java.util.*;
 
// Iterator pattern provides you uniform way of accessing different collections of objects
// If you get array, arraylist and hashtable of objects, you can use iterator to uniformly access them
// You can also write polymorphic code because you can refer to each collection of objects because they implement the same interface

// Iterator Interface
interface Iterator {
    boolean hasNext();
    Object next();
}

// Aggregate Interface
interface Aggregate {
    Iterator createIterator();
}

// Book Class
class Book {
    String title;
    double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }
}

// Concrete Aggregate (Library)
class Library implements Aggregate {
    private List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public Iterator createIterator() {
        return new BookIterator(books);
    }
}

// Concrete Iterator (BookIterator)
class BookIterator implements Iterator {
    private List<Book> books;
    private int index = 0;

    public BookIterator(List<Book> books) {
        this.books = books;
    }

    public boolean hasNext() {
        return index < books.size();
    }

    public Object next() {
        return hasNext() ? books.get(index++) : null;
    }
}

// Client Class
public class IteratorClient {
    public static void main(String[] args) {
        Book book1 = new Book("Java Design Patterns", 500);
        Book book2 = new Book("Effective Java", 600);
        Book book3 = new Book("Clean Code", 450);

        Library library = new Library(Arrays.asList(book1, book2, book3));

        Iterator iterator = library.createIterator();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println("Book: " + book.title + ", Price: " + book.price);
        }
    }
}
