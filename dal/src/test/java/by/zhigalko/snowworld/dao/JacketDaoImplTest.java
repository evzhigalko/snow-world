package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.dao.impl.JacketDaoImpl;
import by.zhigalko.snowworld.entity.EquipmentSize;
import by.zhigalko.snowworld.entity.Image;
import by.zhigalko.snowworld.entity.Jacket;
import by.zhigalko.snowworld.entity.Mitten;
import by.zhigalko.snowworld.model.Gender;
import by.zhigalko.snowworld.model.Product;
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
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import static org.junit.jupiter.api.Assertions.*;

class JacketDaoImplTest {
    private static JacketDaoImpl jacketDao;
    private static ApplicationContext context;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        jacketDao = context.getBean("jacketDao", JacketDaoImpl.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @BeforeEach
    void setUp() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from Jacket");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @AfterAll
    static void closeSession() {
        sessionFactory.close();
    }

    @Test
    void saveTest() {
        //GIVEN
        Jacket expected = getJacket();
        //WHEN
        jacketDao.save(expected);
        //THEN
        Jacket actual = findJacket(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        Jacket expected = getJacket();
        saveJacket(expected);
        //WHEN
        Jacket actual = jacketDao.findById(expected.getId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //GIVEN
        Jacket jacket1 = getJacket();
        Jacket jacket2 = getJacket();
        Jacket jacket3 = getJacket();
        saveJacket(jacket1);
        saveJacket(jacket2);
        saveJacket(jacket3);
        List<Jacket> expected = List.of(jacket1, jacket2, jacket3);
        //WHEN
        List<Jacket> actual = jacketDao.findAll(0, 3);
        //THEN
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(3, actual.size());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void updateTest() {
        //GIVEN
        Jacket expected = getJacket();
        saveJacket(expected);
        //WHEN
        expected.setCost(777);
        expected.setMaker("TEST");
        jacketDao.update(expected);
        //THEN
        Jacket actual = findJacket(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        Jacket expected = getJacket();
        saveJacket(expected);
        //WHEN
        Jacket actual = jacketDao.updateAvailable(expected, false);
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.isAvailableToRental(), actual.isAvailableToRental());
        assertEquals(expected, actual);
    }

    @Test
    void updateCostTest() {
        //GIVEN
        Jacket expected = getJacket();
        saveJacket(expected);
        //WHEN
        Jacket actual = jacketDao.updateCost(expected, 777.0);
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getCost(),actual.getCost());
        assertEquals(expected, actual);
    }

    @Test
    void deleteTest() {
        //GIVEN
        Jacket expected = getJacket();
        saveJacket(expected);
        //WHEN
        jacketDao.delete(expected);
        //THEN
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<Mitten> actual = session.createQuery("select j from Jacket as j ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        Jacket jacket1 = getJacket();
        Jacket jacket2 = getJacket();
        Jacket jacket3 = getJacket();
        saveJacket(jacket1);
        saveJacket(jacket2);
        saveJacket(jacket3);
        //WHEN
        long actual = jacketDao.count();
        //THEN
        assertEquals(3L, actual);
    }
    private Jacket getJacket() {
        AtomicLong counter = new AtomicLong(0);
        Jacket jacket = new Jacket();
        jacket.setProductName(Product.JACKET);
        jacket.setMaker("QUIKSILVER");
        jacket.setMembrane(15000);
        jacket.setGender(Gender.MALE);
        jacket.setAvailableToRental(true);
        jacket.setCost(7.0);
        Image image = new Image();
        image.setImageName("imgJacket" + counter.incrementAndGet() + ".png");
        image.addItem(jacket);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("M");
        equipmentSize.addItem(jacket);
        return jacket;
    }

    private Jacket findJacket(UUID id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select j from Jacket as j where id = :jacket_id ");
        query.setParameter("jacket_id", id);
        Jacket actual = (Jacket) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }

    private void saveJacket(Jacket expected) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}
