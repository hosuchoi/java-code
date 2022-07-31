package org.lake.javacode.proxy.pattern;

public class DefaultLookService implements LookService{

    public void rent(Look look){
        System.out.println("rent: " + look.getTitle() + "");
    }

    @Override
    public void returnLook(Look look) {
        System.out.println("return: " + look.getTitle() + "");
    }
}
