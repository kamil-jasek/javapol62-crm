package pl.sda.crm.util;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public abstract class WithEntityManagerTest {

    @Autowired
    protected EntityManager em;

    protected void persist(Object entity) {
        em.persist(entity);
        em.flush();
        em.clear();
    }
}
