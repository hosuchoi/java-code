package org.lake.javacode.reflection.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //해당 어노테이션 사용 영역
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD}) //해당 어노테이션 사용 타켓
public @interface AnotherAnnotation {
    String value() default "lake";
}
