package org.lake.javacode.proxy.classpattern;

import org.springframework.stereotype.Service;

@Service
public class CookService {

    public void rent(Cook cook) {
        System.out.println("rent: " + cook.getTitle() + "");
    }

    public void returnCook(Cook cook) {
        System.out.println("return: " + cook.getTitle() + "");
    }
}
