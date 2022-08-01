package org.lake.javacode.reflection.annotation;

import java.lang.annotation.*;

/**
 * @Retention(RetentionPolicy.Class)가 defualt 값이다
 * 해당 어노테이션은 소스와 클래스에는 남아 있지만
 * 바이트코드를 메모리상에 로딩 했을때는 어노테이션 정보는 빼고 읽어온다.
 * BUT 런타임시에도 읽어오고 싶으면 @Retention(RetentionPolicy.RUNTIME) 줘야한다.
 * - SOURCE: 소스코드까지만 유지 (즉, 컴파일 과정에서 어노테이션 정보는 사라짐)
 * - CLASS: 클래스파일 까지만 유지 (런타임시 유지안됨)
 * - RUNTIME: 런타임 시점까지 유지 (Reflection API 로 어노테이션 정보 조회 가능)
 *
 * SOURCE 정책
 * Getter / Setter 같은 경우 롬복이 바이트 '코드를 생성'해서 넣어주는 것이기 때문에, 
 * 굳이 바이트코드에 어노테이션 정보가 들어갈 필요가 없습니다.
 * 왜냐하면 롬복이 코드를 생성해주니까..
 *
 * RUNTIME 정책
 * 런타임에 어노테이션 정보를 뽑아 쓸수 있다는 의미입니다. 
 * 즉, Reflection API 등을 사용하여 어노테이션 정보를 알수가 있다는 의미입니다. 
 * 스프링을 예로 들자면, @Controller, @Service, @Autowired 등이 있습니다. 
 * 스프링이 올라오는 실행 중인 시점에 컴포넌트 스캔이 가능해야하기 때문에 RUNTIME 정책이 필요합니다. 
 * 스프링도 내부적으로 Reflection 등을 활용하여 어노테이션이 붙은 놈들만 가져옵니다.
 *
 * CLASS 정책
 * 그러면, CLASS 정책은 왜 필요한지 궁금하실거에요. "아니 Reflection 같은걸로 정보를 얻을수도 없으면서 왜 필요한거지?"
 * 인텔리제이를 써보셨다면, @NonNull 등이 붙어있는 경우 null 값을 넣게되면 노랑색 경고로 알려줍니다.
 * "아니 그러면 SOURCE로 해도 될거 같은데?" 싶으실텐데요, 
 * 중요한점은 Maven/Gradle로 다운받은 라이브러리와 같이 jar 파일에는 소스가 포함되어있지 않다는 점입니다. 
 * class 파일만 포함되어있죠
 * 즉, class 파일만 존재하는 라이브러리 같은 경우에도 타입체커, IDE 부가기능 등을 사용할수 있으려면 CLASS 정책이 필요하게 됩니다. 
 * SOURCE 정책으로 사용한다면 컴파일된 라이브러리의 jar 파일에는 어노테이션 정보가 남아있지 않기 때문이죠.
 * 그외에도 클래스로딩시 무언가를 하고 싶은 경우에도 사용될수도 있고요
 *
 * Target의 기능은 어노테이션을 붙일 수 있는 대상을 지정하는 것이다.
 * 위의 매개 변수로는 TYPE / CONSTRUCTOR / METHOD / FIELD
 * CONSTRUCTOR / METHOD / FIELD 3 가지는 이름 그대로
 * 생성자와 메소드 필드에 어노테이션을 붙일 수 있다는 의미이며,
 * TYPE 는 클래스,인터페이스,열거타입에 어노테이션을 붙일 수 있다는 의미이다.
 */
@Retention(RetentionPolicy.RUNTIME) //해당 어노테이션 사용 영역
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD}) //해당 어노테이션 사용 타켓
@Inherited // 상속이 가능한 어노테이션
public @interface MyAnnotation {

    String name() default "lake";
    int number() default  40;
    String value() default "1"; // value라고 쓰면 해당 어노테이션 사용시 변수명 안적어도 됨
}
