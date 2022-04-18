package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.dao.impl.EquipmentSizeDaoImpl;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.config.ApplicationConfig;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.jupiter.api.Assertions.*;

class EquipmentSizeDaoImplTest {
    private static EquipmentSizeDao equipmentSizeDao;
    private static ApplicationContext context;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        equipmentSizeDao = context.getBean("equipmentSizeDao", EquipmentSizeDaoImpl.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @BeforeEach
    void setUp() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from EquipmentSize");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @AfterAll
    static void closeSession() {
        sessionFactory.close();
    }

    @Test
    void findByIdTest() {
        //GIVEN
        EquipmentSize expected = new EquipmentSize();
        expected.setEquipmentSizeId("SN160");
        expected.setUserMinHeight(175);
        expected.setUserMaxHeight(195);
        expected.setUserMinWeight(80);
        expected.setUserMaxWeight(110);
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
        //WHEN
        EquipmentSize actual = equipmentSizeDao.findById(expected.getEquipmentSizeId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getEquipmentSizeId());
        assertEquals(expected, actual);
    }
}
