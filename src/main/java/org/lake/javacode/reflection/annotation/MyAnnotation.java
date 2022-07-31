package org.lake.javacode.reflection.annotation;

import java.lang.annotation.*;

/**
 * 어노테이션은 소스와 클래스에는 남아 있지만
 * 바이트코드를 메모리상에 로딩 했을때는 어노테이션 정보는 빼고 읽어옴
 * BUT 런타임시에도 읽어오고 싶으면 @Retention(RetentionPolicy.RUNTIME) 줘야함
 */
@Retention(RetentionPolicy.RUNTIME) //해당 어노테이션 사용 영역
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD}) //해당 어노테이션 사용 타켓
@Inherited // 상속이 가능한 어노테이션
public @interface MyAnnotation {

    String name() default "lake";
    int number() default  40;
    String value() default "1"; // value라고 쓰면 해당 어노테이션 사용시 변수명 안적어도 됨
}
