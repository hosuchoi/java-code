package org.lake.javacode.reflection.basic;

public class Mood {

    public static String A = "A";
    private String B = "B";

    public Mood() {
    }

    public Mood(String b) {
        B = b;
    }

    private void c(){
        System.out.println("method c");
    }

    public int sum(int left, int right){
        return left + right;
    }
}
