package org.lake.javacode.reflection.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {

    public static void main(String[] args) throws Exception {
        //type을 통한 접근
        Class<Book> bookClass1 = Book.class;

        //인스턴스를 통한 접근
        Book book2 = new Book();
        Class<? extends Book> aClass = book2.getClass();

        //문자열 내용뿐이 모를때는 FQCN (Full Qualified Class Name) 방식으로 접근
        Class<?> aClass3 = Class.forName("org.lake.javacode.reflection.basic.Book");

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

        /**
         * Reflection set
         */
        Class<?> moodClass = Class.forName("org.lake.javacode.reflection.basic.Mood");
//        moodClass.newInstance(); //deprecated 되었으므로 객체 생성시 이방법은 비추!! 아래 생성자 호출 방식으로 객체 생성
        Constructor<?> constructor = moodClass.getConstructor(String.class);
        Mood mood = (Mood) constructor.newInstance("myMood");
        System.out.println(mood);

        /**
         * Reflection Set Filed
         */
        Field a = Mood.class.getDeclaredField("A");
        System.out.println(a.get(null)); //A는 static 변수이므로 null값 넘겨줌 (static 변수는 모든 객체가 공유하는 변수로 특정 객체 불필요함)
        a.set(null, "AAAAA"); //set도 마찬가지로 null을 넘겨줌
        System.out.println(a.get(null));

        Field b = Mood.class.getDeclaredField("B");
        b.setAccessible(true); //private을 접근하기 위한 설정
        System.out.println(b.get(mood)); //기본 변수의 경우 사용될 객체를 넘겨줘야됨
        b.set(mood, "BBBBB");
        System.out.println(b.get(mood));

        /**
         * Reflection Set Method
         */
        Method c = Mood.class.getDeclaredMethod("c"); //파라미터가 없는 메서드 호출
        c.setAccessible(true);
        c.invoke(mood);

        Method sum = Mood.class.getDeclaredMethod("sum", int.class, int.class); //파라미터와 리턴타입이 있는 메서드 호출
        int result = (int)sum.invoke(mood,2,3);
        System.out.println(result);
    }
}
