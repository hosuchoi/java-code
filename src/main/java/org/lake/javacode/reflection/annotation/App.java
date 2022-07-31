package org.lake.javacode.reflection.annotation;

import java.util.Arrays;


public class App {

    public static void main(String[] args) {
        System.out.println("===========Mood getAnnotations");
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println); //현재 자신의 어노테이션 조회
        System.out.println("===========MyBook getAnnotations");
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println); //상속에의한 어노테이션 + 자신의 어노테이션
        System.out.println("===========getDeclaredAnnotations");
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println); //상속은 제외 + 자신의 어노테이션
        System.out.println("============활용");
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                if(a instanceof MyAnnotation){
                    MyAnnotation a1 = (MyAnnotation) a;
                    System.out.println(a1.value());
                    System.out.println(a1.name());
                    System.out.println(a1.number());
                }
            }); //필드에 붙은 어노테이션 조회
        });
    }
}
