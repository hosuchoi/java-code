package org.lake.javacode.proxy.jpaproxy;

import org.springframework.stereotype.Service;

@Service
public class PookService {

    PookRepository pookRepository;

    public PookService(PookRepository pookRepository) {
        this.pookRepository = pookRepository;
    }
}
