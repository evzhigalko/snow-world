package by.zhigalko.snow.world.dal.dao.snowboard;

import by.zhigalko.snow.world.dal.entity.EquipmentSize;
import by.zhigalko.snow.world.dal.entity.Image;
import by.zhigalko.snow.world.dal.entity.enums.Gender;
import by.zhigalko.snow.world.dal.entity.enums.ProductGroup;
import by.zhigalko.snow.world.dal.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.dal.entity.snowboard.SnowboardHelmet;
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

class SnowboardHelmetDaoImplTest {
    private static SnowboardHelmetDaoImpl snowboardHelmetDao;
    @BeforeAll
    static void init() {
        snowboardHelmetDao = SnowboardHelmetDaoImpl.getInstance();
    }

    @BeforeEach
    void setUp() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM SnowboardHelmet WHERE TRUE");
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
        SnowboardHelmet expected = getSnowboardHelmet();
        //WHEN
        snowboardHelmetDao.save(expected);
        //THEN
        SnowboardHelmet actual = findSnowboardHelmet(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        SnowboardHelmet expected = getSnowboardHelmet();
        saveSnowboardHelmet(expected);
        //WHEN
        SnowboardHelmet actual = snowboardHelmetDao.findById(expected.getId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //GIVEN
        SnowboardHelmet snowboardHelmet1 = getSnowboardHelmet();
        SnowboardHelmet snowboardHelmet2 = getSnowboardHelmet();
        SnowboardHelmet snowboardHelmet3 = getSnowboardHelmet();
        saveSnowboardHelmet(snowboardHelmet1);
        saveSnowboardHelmet(snowboardHelmet2);
        saveSnowboardHelmet(snowboardHelmet3);
        List<SnowboardHelmet> expected = List.of(snowboardHelmet1, snowboardHelmet2, snowboardHelmet3);
        //WHEN
        List<SnowboardHelmet> actual = snowboardHelmetDao.findAll(0, 3);
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
        SnowboardHelmet expected = getSnowboardHelmet();
        saveSnowboardHelmet(expected);
        //WHEN
        expected.setCost(777);
        expected.setMaker("TEST");
        snowboardHelmetDao.update(expected);
        //THEN
        SnowboardHelmet actual = findSnowboardHelmet(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        SnowboardHelmet expected = getSnowboardHelmet();
        saveSnowboardHelmet(expected);
        //WHEN
        SnowboardHelmet actual = snowboardHelmetDao.updateAvailable(expected, false);
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
        SnowboardHelmet expected = getSnowboardHelmet();
        saveSnowboardHelmet(expected);
        //WHEN
        SnowboardHelmet actual = snowboardHelmetDao.updateCost(expected, 777.0);
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
        SnowboardHelmet expected = getSnowboardHelmet();
        saveSnowboardHelmet(expected);
        //WHEN
        snowboardHelmetDao.delete(expected);
        //THEN
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        List<Snowboard> actual = session.createQuery("select snb from SnowboardHelmet as snb ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        SnowboardHelmet snowboardHelmet1 = getSnowboardHelmet();
        SnowboardHelmet snowboardHelmet2 = getSnowboardHelmet();
        SnowboardHelmet snowboardHelmet3 = getSnowboardHelmet();
        saveSnowboardHelmet(snowboardHelmet1);
        saveSnowboardHelmet(snowboardHelmet2);
        saveSnowboardHelmet(snowboardHelmet3);
        //WHEN
        long actual = snowboardHelmetDao.count();
        //THEN
        assertEquals(3L, actual);
    }

    private SnowboardHelmet getSnowboardHelmet() {
        AtomicLong counter = new AtomicLong(0);
        SnowboardHelmet snowboardHelmet = new SnowboardHelmet();
        snowboardHelmet.setProductName(ProductGroup.SNOWBOARD_HELMET);
        snowboardHelmet.setMaker("PRIME");
        snowboardHelmet.setGender(Gender.UNISEX);
        snowboardHelmet.setAvailableToRental(true);
        snowboardHelmet.setCost(3.0);
        Image image = new Image();
        image.setImageName("imgSnbHelmet" + counter.incrementAndGet() + ".png");
        image.addImage(snowboardHelmet);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("L");
        equipmentSize.addEquipment(snowboardHelmet);
        return snowboardHelmet;
    }

    private SnowboardHelmet findSnowboardHelmet(UUID id) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select snb from SnowboardHelmet AS snb where id = :snowboard_helmet_id ");
        query.setParameter("snowboard_helmet_id", id);
        SnowboardHelmet actual = (SnowboardHelmet) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }

    private void saveSnowboardHelmet(SnowboardHelmet expected) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}