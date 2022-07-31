package org.lake.javacode.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {

    public static void main(String[] args) throws ClassNotFoundException {
        //type을 통한 접근
        Class<Book> bookClass1 = Book.class;

        //인스턴스를 통한 접근
        Book book2 = new Book();
        Class<? extends Book> aClass = book2.getClass();

        //문자열 내용뿐이 모를때는 FQCN (Full Qualified Class Name) 방식으로 접근
        Class<?> aClass3 = Class.forName("org.lake.javacode.reflection.Book");

        /**
         * Reflection Filed
         */
//        getFileds()는 public 변수만 가져옴
        System.out.println("=========getFileds()");
        Arrays.stream(bookClass1.getFields()).forEach(System.out::println);
//        getDeclaredFields()는 모든 변수를 가져옴
        System.out.println("=========getDeclaredFields()");
        Arrays.stream(bookClass1.getDeclaredFields()).forEach(System.out::println);
//        모든 필드와 값 가져오기 (인스턴트가 존재하여야 값을 가져올수 있음 )
        System.out.println("=========활용");
        Arrays.stream(aClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true); // setAccessible true해야 public외의 모든 제어자의 변수를 가져올 수 있음
                System.out.printf("filed : %s // value : %s\n", f, f.get(book2));
                //modifiers() 이용  ( method에서도 사용 가능 )
                int modifiers = f.getModifiers();
                System.out.println("isPrivate() >> " + Modifier.isPrivate(modifiers));
                System.out.println("isPublic() >> " + Modifier.isPublic(modifiers));
                System.out.println("isProtected() >> " + Modifier.isProtected(modifiers));
                System.out.println("isStatic() >> " + Modifier.isStatic(modifiers));
                //Annotation
                System.out.println(f.getAnnotatedType());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        /**
         * Reflection Method
         */
        System.out.println("==========getMethods()");
        Arrays.stream(bookClass1.getMethods()).forEach(System.out::println);
        System.out.println("==========getDeclaredMethods()");
        Arrays.stream(bookClass1.getDeclaredMethods()).forEach(System.out::println);
        System.out.println("==========활용");
        Arrays.stream(bookClass1.getDeclaredMethods()).forEach(m -> {
            System.out.println("return type >> " + m.getReturnType());
            System.out.println("parameter type >> " + m.getParameterTypes());
        });

        /**
         * Reflection Constructor
         */
        System.out.println("=========getConstructors()");
        Arrays.stream(bookClass1.getConstructors()).forEach(System.out::println);
        System.out.println("=========getDeclaredConstructors()");
        Arrays.stream(bookClass1.getDeclaredConstructors()).forEach(System.out::println);

        /**
         * Reflection SuperClass
         */
        //상속의 부모는 한개만 존재하므로 stream 사용불가
        System.out.println("==========getSuperclass()");
        System.out.println(MyBook.class.getSuperclass());

        /**
         * Reflection Interface
         */
        System.out.println("==========getInterfaces()");
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
    }
}
