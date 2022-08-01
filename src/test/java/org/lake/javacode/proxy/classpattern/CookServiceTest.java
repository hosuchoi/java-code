package org.lake.javacode.proxy.classpattern;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class CookServiceTest {

    @Test
    public void di() throws Exception {

        /**
         * class proxy 방식은 클래스가 상속을 기반으로 만든 방식이므로
         * 클래스에 final이 붙거나 기본생성자가 private와 같은
         * 상속을 막는 소스가 존재한다면 사용할 수없다.
         * proxy는 interface를 이용한 사용방법을 추천한다.
         */

        /**
         * class proxy using cglib - this was used in spring aop
         */
        MethodInterceptor handler = new MethodInterceptor() {
            CookService cookService = new CookService();

            @Override
            public Object intercept(Object o, Method method, Object[] arg, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("rent")) {
                    System.out.println("aaaaa");
                    Object invoke = method.invoke(cookService, arg);
                    System.out.println("bbbbb");

                    return invoke;
                }

                return method.invoke(cookService, arg);
            }
        };

        CookService cookService = (CookService) Enhancer.create(CookService.class, handler);

        System.out.println("========cglib");
        Cook cook = new Cook();
        cook.setTitle("spring");
        cookService.rent(cook);
        cookService.returnCook(cook);


        /**
         * class proxy using byte-buddy
         */
        //defualt : create only proxy with no function
//        Class<? extends CookService> proxyClass = new ByteBuddy().subclass(CookService.class)
//                .make().load(CookService.class.getClassLoader()).getLoaded();

        //function provided by method
        Class<? extends CookService> proxyClass = new ByteBuddy().subclass(CookService.class)
                //provide specific functionality to the 'rent' method
                .method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    CookService cookService = new CookService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("rent")) {
                            System.out.println("aaaaaaa");
                            Object invoke = method.invoke(cookService, args);
                            System.out.println("bbbbbbb");

                            return invoke;
                        }

                        return method.invoke(cookService, args);
                    }
                })).make().load(CookService.class.getClassLoader()).getLoaded();

        CookService cookService1 = proxyClass.getConstructor(null).newInstance();

        System.out.println("========bytebuddy");
        Cook cook1 = new Cook();
        cook1.setTitle("spring");
        cookService1.rent(cook1);
        cookService1.returnCook(cook1);
    }
}