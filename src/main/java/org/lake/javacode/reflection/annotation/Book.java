package org.lake.javacode.reflection.annotation;

@MyAnnotation(name = "mood", number = 1, value = "2")
public class Book {

    @MyAnnotation(name = "a", number = 1)
    private String a;
    @MyAnnotation(name = "b", number = 2)
    public String b;

    public Book(){

    }

    public Book(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @MyAnnotation("getA")
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
