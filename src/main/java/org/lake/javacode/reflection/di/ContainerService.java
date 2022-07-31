package org.lake.javacode.reflection.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    // 파라미터로 들어온 클래스 타입과 동일한 클래스 타입으로 리턴하기 위한 제너릭 메서드
    public static <T> T getObject(Class<T> classType) {
        T newInstance = getNewInstance(classType);
        /**
         * @Inject  어노테이션이 붙은 필드에 instance를 생성
         */
        Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
            if(f.getAnnotation(Inject.class) != null){
                f.setAccessible(true);
                Object filedInstance = getNewInstance(f.getType());
                try {
                    f.set(newInstance, filedInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return newInstance;
    }

    private static <T> T getNewInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
