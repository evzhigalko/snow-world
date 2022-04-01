package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.util.ApplicationConfig;
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

class MittenDaoImplTest {
    private static MittenDaoImpl mittenDao;
    private static ApplicationContext context;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        mittenDao = context.getBean("mittenDao", MittenDaoImpl.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @BeforeEach
    void setUp() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from Mitten");
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
        Mitten expected = getMitten();
        //WHEN
        mittenDao.save(expected);
        //THEN
        Mitten actual = findMitten(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        Mitten expected = getMitten();
        saveMitten(expected);
        //WHEN
        Mitten actual = mittenDao.findById(expected.getId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //GIVEN
        Mitten mitten1 = getMitten();
        Mitten mitten2 = getMitten();
        Mitten mitten3 = getMitten();
        saveMitten(mitten1);
        saveMitten(mitten2);
        saveMitten(mitten3);
        List<Mitten> expected = List.of(mitten1, mitten2, mitten3);
        //WHEN
        List<Mitten> actual = mittenDao.findAll(0, 3);
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
        Mitten expected = getMitten();
        saveMitten(expected);
        //WHEN
        expected.setCost(777);
        expected.setMaker("TEST");
        mittenDao.update(expected);
        //THEN
        Mitten actual = findMitten(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        Mitten expected = getMitten();
        saveMitten(expected);
        //WHEN
        Mitten actual = mittenDao.updateAvailable(expected, false);
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
        Mitten expected = getMitten();
        saveMitten(expected);
        //WHEN
        Mitten actual = mittenDao.updateCost(expected, 777.0);
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
        Mitten expected = getMitten();
        saveMitten(expected);
        //WHEN
        mittenDao.delete(expected);
        //THEN
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<Mitten> actual = session.createQuery("select m from Mitten as m ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        Mitten mitten1 = getMitten();
        Mitten mitten2 = getMitten();
        Mitten mitten3 = getMitten();
        saveMitten(mitten1);
        saveMitten(mitten2);
        saveMitten(mitten3);
        //WHEN
        long actual = mittenDao.count();
        //THEN
        assertEquals(3L, actual);
    }
    private Mitten getMitten() {
        AtomicLong counter = new AtomicLong(0);
        Mitten mitten = new Mitten();
        mitten.setProductName(Product.MITTEN);
        mitten.setMaker("BURTON");
        mitten.setMembrane(15000);
        mitten.setGender(Gender.UNISEX);
        mitten.setAvailableToRental(true);
        mitten.setCost(2.0);
        Image image = new Image();
        image.setImageName("imgMit" + counter.incrementAndGet() + ".png");
        image.addItem(mitten);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("M");
        equipmentSize.addItem(mitten);
        return mitten;
    }

    private Mitten findMitten(UUID id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select m from Mitten as m where id = :mitten_id ");
        query.setParameter("mitten_id", id);
        Mitten actual = (Mitten) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }

    private void saveMitten(Mitten expected) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}
