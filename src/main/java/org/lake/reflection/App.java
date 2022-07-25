package org.lake.reflection;

public class App {

    public static void main(String[] args) {
        Class<Book> bookClass = Book.class;

        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

//        Class<?> aClass1 = Class.forName("org.lake.reflection.Book");


    }
}
