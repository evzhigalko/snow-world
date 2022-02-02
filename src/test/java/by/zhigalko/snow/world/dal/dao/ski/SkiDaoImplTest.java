package by.zhigalko.snow.world.dal.dao.ski;

import by.zhigalko.snow.world.dal.entity.EquipmentSize;
import by.zhigalko.snow.world.dal.entity.Image;
import by.zhigalko.snow.world.dal.entity.enums.Gender;
import by.zhigalko.snow.world.dal.entity.enums.ProductGroup;
import by.zhigalko.snow.world.dal.entity.enums.RidingLevel;
import by.zhigalko.snow.world.dal.entity.ski.Ski;
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

class SkiDaoImplTest {
    private static SkiDaoImpl skiDao;

    @BeforeAll
    static void init() {
        skiDao = SkiDaoImpl.getInstance();
    }

    @BeforeEach
    void setUp() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Ski WHERE TRUE");
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
        Ski expected = getSki();
        //WHEN
        skiDao.save(expected);
        //THEN
        Ski actual = findSki(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        Ski expected = getSki();
        saveSki(expected);
        //WHEN
        Ski actual = skiDao.findById(expected.getId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //GIVEN
        Ski ski1 = getSki();
        Ski ski2 = getSki();
        Ski ski3 = getSki();
        saveSki(ski1);
        saveSki(ski2);
        saveSki(ski3);
        List<Ski> expected = List.of(ski1, ski2, ski3);
        //WHEN
        List<Ski> actual = skiDao.findAll(0, 3);
        //THEN
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void updateTest() {
        //GIVEN
        Ski expected = getSki();
        saveSki(expected);
        //WHEN
        expected.setCost(77.0);
        expected.setMaker("TEST");
        skiDao.update(expected);
        //THEN
        Ski actual = findSki(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void deleteTest() {
        //GIVEN
        Ski expected = getSki();
        saveSki(expected);
        //WHEN
        skiDao.delete(expected);
        //THEN
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        List<Ski> actual = session.createQuery("select snb from Ski as snb ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        Ski ski1 = getSki();
        Ski ski2 = getSki();
        Ski ski3 = getSki();
        saveSki(ski1);
        saveSki(ski2);
        saveSki(ski3);
        //WHEN
        long actual = skiDao.count();
        //THEN
        assertEquals(3L, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        Ski expected = getSki();
        saveSki(expected);
        //WHEN
        Ski actual = skiDao.updateAvailable(expected, false);
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.isAvailableToRental(), actual.isAvailableToRental());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void updateCostTest() {
        //GIVEN
        Ski expected = getSki();
        saveSki(expected);
        //WHEN
        Ski actual = skiDao.updateCost(expected, 50.0);
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.getCost(),actual.getCost());
        assertEquals(expected, actual);
    }

    private Ski getSki() {
        AtomicLong counter = new AtomicLong(0);
        Ski ski = new Ski();
        ski.setProductName(ProductGroup.SKI);
        ski.setRidingLevel(RidingLevel.BEGINNER);
        ski.setMaker("ROSSIGNOL");
        ski.setGender(Gender.UNISEX);
        ski.setAvailableToRental(true);
        ski.setCost(10.0);
        Image image = new Image();
        image.setImageName("imgSnbBoot" + counter.incrementAndGet() + ".png");
        image.addImage(ski);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("SK155");
        equipmentSize.setUserMinHeight(165);
        equipmentSize.setUserMaxHeight(170);
        equipmentSize.addEquipment(ski);
        return ski;
    }

    private Ski findSki(Long id) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select s from Ski AS s where id = :ski_id ");
        query.setParameter("ski_id", id);
        Ski actual = (Ski) query.getSingleResult();
        session.getTransaction().commit();
        return actual;
    }

    private void saveSki(Ski expected) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}