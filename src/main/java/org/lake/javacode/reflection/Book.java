package org.lake.javacode.reflection;

public class Book {

    private static String  b = "BOOK";
    private static final String c = "BOOK";
    private String a = "A";
    public String d = "d";
    protected  String e = "e";

    public Book() {
    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f(){
        System.out.println("F");
    }

    public void g(){
        System.out.println("g");
    }

    public int h(){
        return 100;
    }
}
