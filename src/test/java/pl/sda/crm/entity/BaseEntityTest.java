package pl.sda.crm.entity;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
abstract class BaseEntityTest {

    @Autowired
    protected EntityManager em;

    protected void persist(Object entity) {
        em.persist(entity);
        em.flush();
        em.clear();
    }
}
