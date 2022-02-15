package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Jacket;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.util.SessionManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import static org.junit.jupiter.api.Assertions.*;

class JacketDaoImplTest {
    private static JacketDaoImpl jacketDao;
    @BeforeAll
    static void init() {
        jacketDao = JacketDaoImpl.getInstance();
    }

    @BeforeEach
    void setUp() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from Jacket where true");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @AfterAll
    static void closeSession() {
        SessionManager.closeSessionFactory();
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
        Session session = SessionManager.getSession();
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
        jacket.setProductName(ProductGroup.JACKET);
        jacket.setMaker("QUIKSILVER");
        jacket.setMembrane(15000);
        jacket.setGender(Gender.MALE);
        jacket.setAvailableToRental(true);
        jacket.setCost(7.0);
        Image image = new Image();
        image.setImageName("imgJacket" + counter.incrementAndGet() + ".png");
        image.addImage(jacket);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("M");
        equipmentSize.addEquipment(jacket);
        return jacket;
    }

    private Jacket findJacket(UUID id) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select j from Jacket as j where id = :jacket_id ");
        query.setParameter("jacket_id", id);
        Jacket actual = (Jacket) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }

    private void saveJacket(Jacket expected) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}
