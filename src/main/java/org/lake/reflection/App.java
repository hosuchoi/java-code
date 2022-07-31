package org.lake.reflection;

public class App {

    public static void main(String[] args) throws ClassNotFoundException {
        //type을 통한 접근
        Class<Book> bookClass = Book.class;

        //인스턴스를 통한 접근
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        //문자열 내용뿐이 모를때는 FQCN (Full Qualified Class Name) 방식으로 접근
        Class<?> aClass1 = Class.forName("org.lake.reflection.Book");

    }
}
