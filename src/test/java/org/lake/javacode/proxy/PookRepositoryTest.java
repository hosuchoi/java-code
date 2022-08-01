package org.lake.javacode.proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lake.javacode.proxy.jpaproxy.Pook;
import org.lake.javacode.proxy.jpaproxy.PookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PookRepositoryTest {

    @Autowired
    PookRepository pookRepository;

    @Test
    public void di() {
        assertNotNull(pookRepository);

        Pook pook = new Pook();
        pook.setTitle("Spring");
        pookRepository.save(pook);
        List<Pook> all = pookRepository.findAll();
        all.stream().forEach(System.out::println);

    }
}