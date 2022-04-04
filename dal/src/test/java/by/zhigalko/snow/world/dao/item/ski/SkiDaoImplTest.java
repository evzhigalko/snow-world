package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.enums.RidingLevel;
import by.zhigalko.snow.world.entity.ski.Ski;
import by.zhigalko.snow.world.config.ApplicationConfig;
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

class SkiDaoImplTest {
    private static SkiDaoImpl skiDao;
    private static ApplicationContext context;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        skiDao = context.getBean("skiDao", SkiDaoImpl.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @BeforeEach
    void setUp() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from Ski");
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
        Session session = sessionFactory.openSession();
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
        ski.setProductName(Product.SKI);
        ski.setRidingLevel(RidingLevel.BEGINNER);
        ski.setMaker("ROSSIGNOL");
        ski.setGender(Gender.UNISEX);
        ski.setAvailableToRental(true);
        ski.setCost(10.0);
        Image image = new Image();
        image.setImageName("imgSnbBoot" + counter.incrementAndGet() + ".png");
        image.addItem(ski);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("SK155");
        equipmentSize.setUserMinHeight(165);
        equipmentSize.setUserMaxHeight(170);
        equipmentSize.addItem(ski);
        return ski;
    }

    private Ski findSki(UUID id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select s from Ski AS s where id = :ski_id ");
        query.setParameter("ski_id", id);
        Ski actual = (Ski) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }

    private void saveSki(Ski expected) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}
