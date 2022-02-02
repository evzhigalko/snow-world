package by.zhigalko.snow.world.dal.dao.ski;

import by.zhigalko.snow.world.dal.entity.EquipmentSize;
import by.zhigalko.snow.world.dal.entity.Image;
import by.zhigalko.snow.world.dal.entity.enums.Gender;
import by.zhigalko.snow.world.dal.entity.enums.ProductGroup;
import by.zhigalko.snow.world.dal.entity.ski.SkiHelmet;
import by.zhigalko.snow.world.dal.entity.snowboard.Snowboard;
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

class SkiHelmetDaoImplTest {
    private static SkiHelmetDaoImpl skiHelmetDao;
    @BeforeAll
    static void init() {
        skiHelmetDao = SkiHelmetDaoImpl.getInstance();
    }

    @BeforeEach
    void setUp() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM SkiHelmet WHERE TRUE");
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
        SkiHelmet expected = getSkiHelmet();
        //WHEN
        skiHelmetDao.save(expected);
        //THEN
        SkiHelmet actual = findSkiHelmet(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        SkiHelmet expected = getSkiHelmet();
        saveSkiHelmet(expected);
        //WHEN
        SkiHelmet actual = skiHelmetDao.findById(expected.getId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //GIVEN
        SkiHelmet skiHelmet1 = getSkiHelmet();
        SkiHelmet skiHelmet2 = getSkiHelmet();
        SkiHelmet skiHelmet3 = getSkiHelmet();
        saveSkiHelmet(skiHelmet1);
        saveSkiHelmet(skiHelmet2);
        saveSkiHelmet(skiHelmet3);
        List<SkiHelmet> expected = List.of(skiHelmet1, skiHelmet2, skiHelmet3);
        //WHEN
        List<SkiHelmet> actual = skiHelmetDao.findAll(0, 3);
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
        SkiHelmet expected = getSkiHelmet();
        saveSkiHelmet(expected);
        //WHEN
        expected.setCost(777);
        expected.setMaker("TEST");
        skiHelmetDao.update(expected);
        //THEN
        SkiHelmet actual = findSkiHelmet(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        SkiHelmet expected = getSkiHelmet();
        saveSkiHelmet(expected);
        //WHEN
        SkiHelmet actual = skiHelmetDao.updateAvailable(expected, false);
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
        SkiHelmet expected = getSkiHelmet();
        saveSkiHelmet(expected);
        //WHEN
        SkiHelmet actual = skiHelmetDao.updateCost(expected, 777.0);
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
        SkiHelmet expected = getSkiHelmet();
        saveSkiHelmet(expected);
        //WHEN
        skiHelmetDao.delete(expected);
        //THEN
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        List<Snowboard> actual = session.createQuery("select skh from SkiHelmet as skh ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        SkiHelmet skiHelmet1 = getSkiHelmet();
        SkiHelmet skiHelmet2 = getSkiHelmet();
        SkiHelmet skiHelmet3 = getSkiHelmet();
        saveSkiHelmet(skiHelmet1);
        saveSkiHelmet(skiHelmet2);
        saveSkiHelmet(skiHelmet3);
        //WHEN
        long actual = skiHelmetDao.count();
        //THEN
        assertEquals(3L, actual);
    }

    private SkiHelmet getSkiHelmet() {
        AtomicLong counter = new AtomicLong(0);
        SkiHelmet skiHelmet = new SkiHelmet();
        skiHelmet.setProductName(ProductGroup.SKI_HELMET);
        skiHelmet.setMaker("Glissade");
        skiHelmet.setGender(Gender.UNISEX);
        skiHelmet.setAvailableToRental(true);
        skiHelmet.setCost(3.0);
        Image image = new Image();
        image.setImageName("imgSkiHelmet" + counter.incrementAndGet() + ".png");
        image.addImage(skiHelmet);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("XS");
        equipmentSize.addEquipment(skiHelmet);
        return skiHelmet;
    }

    private SkiHelmet findSkiHelmet(Long id) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select skh from SkiHelmet AS skh where id = :ski_helmet_id ");
        query.setParameter("ski_helmet_id", id);
        SkiHelmet actual = (SkiHelmet) query.getSingleResult();
        session.getTransaction().commit();
        return actual;
    }

    private void saveSkiHelmet(SkiHelmet expected) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}