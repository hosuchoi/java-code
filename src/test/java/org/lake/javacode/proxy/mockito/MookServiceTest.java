package org.lake.javacode.proxy.mockito;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MookServiceTest {

    @Test
    public void di() {
        /**
         * Mokito used the proxy
         */
        MookRepository mookRepositoryMock = mock(MookRepository.class);
        Mook hibernateMook = new Mook();
        hibernateMook.setTitle("Hibernate");
        when(mookRepositoryMock.save(any())).thenReturn(hibernateMook);

        MookService mookService = new MookService(mookRepositoryMock);

        Mook mook = new Mook();
        mook.setTitle("spring");
        mookService.rent(mook);
        mookService.returnMook(mook);
    }

}