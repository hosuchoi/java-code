package org.lake.javacode.proxy.pattern;

import org.lake.javacode.reflection.di.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class LookServiceProxy implements LookService{

    LookService lookService;

    public LookServiceProxy(LookService lookService) {
        this.lookService = lookService;
    }

    /**
     *  이렇게 class로 proxy를 구성시에는
     *  아래와 같이 동일한 기능이 여러 메서드에 들어가게 될 경우
     *  System.out.println("aaaaa");
     *  System.out.println("bbbbb");
     *  동일한 소스코드가 중복되게 된다
     *  >> 이 문제를 해결 한것이 dynamic proxy로 runtime시 동적으로 생성
     */

    @Override
    public void rent(Look look) {
        System.out.println("aaaaa");
        lookService.rent(look);
        System.out.println("bbbbb");
    }

    @Override
    public void returnLook(Look look) {
        lookService.returnLook(look);
    }
}
