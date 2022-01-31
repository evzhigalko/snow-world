package by.zhigalko.snow.world.dal.dao.clothes;

import by.zhigalko.snow.world.dal.entity.EquipmentSize;
import by.zhigalko.snow.world.dal.entity.Image;
import by.zhigalko.snow.world.dal.entity.clothes.Cap;
import by.zhigalko.snow.world.dal.entity.clothes.Glove;
import by.zhigalko.snow.world.dal.entity.enums.Gender;
import by.zhigalko.snow.world.dal.entity.enums.ProductGroup;
import by.zhigalko.snow.world.util.SessionManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import static org.junit.jupiter.api.Assertions.*;

class GloveDaoImplTest {
    private static GloveDaoImpl gloveDao;
    @BeforeAll
    static void init() {
        gloveDao = new GloveDaoImpl();
    }

    @BeforeEach
    void setUp() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Glove WHERE TRUE");
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @AfterAll
    static void closeSession() {
        SessionManager.closeSessionFactory();
    }

    @Test
    void saveTest() {
        //GIVEN
        Glove expected = getGlove();
        //WHEN
        gloveDao.save(expected);
        //THEN
        Glove actual = findGlove(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        Glove expected = getGlove();
        saveGlove(expected);
        //WHEN
        Glove actual = gloveDao.findById(expected.getId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //GIVEN
        Glove glove1 = getGlove();
        Glove glove2 = getGlove();
        Glove glove3 = getGlove();
        saveGlove(glove1);
        saveGlove(glove2);
        saveGlove(glove3);
        List<Glove> expected = List.of(glove1, glove2, glove3);
        //WHEN
        List<Glove> actual = gloveDao.findAll(0, 3);
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
        Glove expected = getGlove();
        saveGlove(expected);
        //WHEN
        expected.setCost(777);
        expected.setMaker("TEST");
        gloveDao.update(expected);
        //THEN
        Glove actual = findGlove(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        Glove expected = getGlove();
        saveGlove(expected);
        //WHEN
        Glove actual = gloveDao.updateAvailable(expected, false);
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
        Glove expected = getGlove();
        saveGlove(expected);
        //WHEN
        Glove actual = gloveDao.updateCost(expected, 777.0);
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
        Glove expected = getGlove();
        saveGlove(expected);
        //WHEN
        gloveDao.delete(expected);
        //THEN
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        List<Cap> actual = session.createQuery("select gl from Glove as gl ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        Glove glove1 = getGlove();
        Glove glove2 = getGlove();
        Glove glove3 = getGlove();
        saveGlove(glove1);
        saveGlove(glove2);
        saveGlove(glove3);
        //WHEN
        long actual = gloveDao.count();
        //THEN
        assertEquals(3L, actual);
    }

    private Glove getGlove() {
        AtomicLong counter = new AtomicLong(0);
        Glove glove = new Glove();
        glove.setProductName(ProductGroup.GLOVE);
        glove.setMaker("POW");
        glove.setMembrane(15000);
        glove.setGender(Gender.UNISEX);
        glove.setAvailableToRental(true);
        glove.setCost(2.0);
        Image image = new Image();
        image.setImageName("imgGlove" + counter.incrementAndGet() + ".png");
        image.addImage(glove);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("XS");
        equipmentSize.addEquipment(glove);
        return glove;
    }

    private Glove findGlove(Long id) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select gl from Glove AS gl where id = :glove_id ");
        query.setParameter("glove_id", id);
        Glove actual = (Glove) query.getSingleResult();
        session.getTransaction().commit();
        return actual;
    }

    private void saveGlove(Glove expected) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}