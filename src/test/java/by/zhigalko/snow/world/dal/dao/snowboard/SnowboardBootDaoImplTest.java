package by.zhigalko.snow.world.dal.dao.snowboard;

import by.zhigalko.snow.world.dal.entity.EquipmentSize;
import by.zhigalko.snow.world.dal.entity.Image;
import by.zhigalko.snow.world.dal.entity.enums.*;
import by.zhigalko.snow.world.dal.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.dal.entity.snowboard.SnowboardBoot;
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

class SnowboardBootDaoImplTest {
    private static SnowboardBootDaoImpl snowboardBootDao;

    @BeforeAll
    static void init() {
        snowboardBootDao = SnowboardBootDaoImpl.getInstance();
    }

    @BeforeEach
    void setUp() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM SnowboardBoot WHERE TRUE");
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
        SnowboardBoot expected = getSnowboardBoot();
        //WHEN
        snowboardBootDao.save(expected);
        //THEN
        SnowboardBoot actual = findSnowboardBoot(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        SnowboardBoot expected = getSnowboardBoot();
        saveSnowboardBoot(expected);
        //WHEN
        SnowboardBoot actual = snowboardBootDao.findById(expected.getId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //GIVEN
        SnowboardBoot snowboardBoot1 = getSnowboardBoot();
        SnowboardBoot snowboardBoot2 = getSnowboardBoot();
        SnowboardBoot snowboardBoot3 = getSnowboardBoot();
        saveSnowboardBoot(snowboardBoot1);
        saveSnowboardBoot(snowboardBoot2);
        saveSnowboardBoot(snowboardBoot3);
        List<SnowboardBoot> expected = List.of(snowboardBoot1, snowboardBoot2, snowboardBoot3);
        //WHEN
        List<SnowboardBoot> actual = snowboardBootDao.findAll(0, 3);
        //THEN
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(3, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    void updateTest() {
        //GIVEN
        SnowboardBoot expected = getSnowboardBoot();
        saveSnowboardBoot(expected);
        //WHEN
        expected.setCost(777);
        expected.setMaker("TEST");
        snowboardBootDao.update(expected);
        //THEN
        SnowboardBoot actual = findSnowboardBoot(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        SnowboardBoot expected = getSnowboardBoot();
        saveSnowboardBoot(expected);
        //WHEN
        SnowboardBoot actual = snowboardBootDao.updateAvailable(expected, false);
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
        SnowboardBoot expected = getSnowboardBoot();
        saveSnowboardBoot(expected);
        //WHEN
        SnowboardBoot actual = snowboardBootDao.updateCost(expected, 777.0);
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
        SnowboardBoot expected = getSnowboardBoot();
        saveSnowboardBoot(expected);
        //WHEN
        snowboardBootDao.delete(expected);
        //THEN
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        List<Snowboard> actual = session.createQuery("select snb from SnowboardBoot as snb ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        SnowboardBoot snowboardBoot1 = getSnowboardBoot();
        SnowboardBoot snowboardBoot2 = getSnowboardBoot();
        SnowboardBoot snowboardBoot3 = getSnowboardBoot();
        saveSnowboardBoot(snowboardBoot1);
        saveSnowboardBoot(snowboardBoot2);
        saveSnowboardBoot(snowboardBoot3);
        //WHEN
        long actual = snowboardBootDao.count();
        //THEN
        assertEquals(3L, actual);
    }

    private SnowboardBoot getSnowboardBoot() {
        AtomicLong counter = new AtomicLong(0);
        SnowboardBoot snowboardBoot = new SnowboardBoot();
        snowboardBoot.setProductName(ProductGroup.SNOWBOARD_BOOT);
        snowboardBoot.setMaker("NITRO");
        snowboardBoot.setGender(Gender.MALE);
        snowboardBoot.setLacingSystem(LacingSystem.BOA);
        snowboardBoot.setAvailableToRental(true);
        snowboardBoot.setCost(7.0);
        Image image = new Image();
        image.setImageName("imgSnbBoot" + counter.incrementAndGet() + ".png");
        image.addImage(snowboardBoot);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("43");
        equipmentSize.addEquipment(snowboardBoot);
        return snowboardBoot;
    }

    private SnowboardBoot findSnowboardBoot(Long id) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select snb from SnowboardBoot AS snb where id = :snowboard_boot_id ");
        query.setParameter("snowboard_boot_id", id);
        SnowboardBoot actual = (SnowboardBoot) query.getSingleResult();
        session.getTransaction().commit();
        return actual;
    }

    private void saveSnowboardBoot(SnowboardBoot expected) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}
