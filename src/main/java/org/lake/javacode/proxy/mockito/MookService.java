package org.lake.javacode.proxy.mockito;

import org.springframework.stereotype.Service;

@Service
public class MookService {

    MookRepository mookRepository;

    public MookService(MookRepository mookRepository) {
        this.mookRepository = mookRepository;
    }

    public void rent(Mook mook) {
        Mook savedMook = mookRepository.save(mook);
        System.out.println("rent: " + savedMook.getTitle() + "");
    }

    public void returnMook(Mook mook) {
        Mook savedMook = mookRepository.save(mook);
        System.out.println("return: " + savedMook.getTitle() + "");
    }
}
