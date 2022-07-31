package org.lake.javacode.proxy.pattern;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class DefaultLookServiceTest {

    /**
     * Class Proxy Type
     */
    LookService lookService = new LookServiceProxy(new DefaultLookService());

    /**
     * Dynamic Proxy Type
     */
    LookService dynmicLookService = (LookService) Proxy.newProxyInstance(
            LookService.class.getClassLoader(),
            new Class[]{LookService.class},
            new InvocationHandler() {
                LookService lookService = new DefaultLookService();
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if(method.getName().equals("rent")){
                        System.out.println("aaaaa");
                        Object invoke = method.invoke(lookService, args);
                        System.out.println("bbbbb");

                        return invoke;
                    }

                    return method.invoke(lookService, args);
                }
            });

    @Test
    public void di() {
        Look look = new Look();
        look.setTitle("spring");
        System.out.println("========= class proxy");
        lookService.rent(look);
        lookService.returnLook(look);

        System.out.println("========= dynamic proxy");
        dynmicLookService.rent(look);
        dynmicLookService.returnLook(look);
    }
}