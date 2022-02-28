package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Mitten;
import by.zhigalko.snow.world.entity.clothes.Pants;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
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

class PantsDaoImplTest {
    private static PantsDaoImpl pantsDao;
    private static ApplicationContext context;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        pantsDao = context.getBean("pantsDao", PantsDaoImpl.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @BeforeEach
    void setUp() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from Pants");
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
        Pants expected = getPants();
        //WHEN
        pantsDao.save(expected);
        //THEN
        Pants actual = findPants(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        Pants expected = getPants();
        savePants(expected);
        //WHEN
        Pants actual = pantsDao.findById(expected.getId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //GIVEN
        Pants pants1 = getPants();
        Pants pants2 = getPants();
        Pants pants3 = getPants();
        savePants(pants1);
        savePants(pants2);
        savePants(pants3);
        List<Pants> expected = List.of(pants1, pants2, pants3);
        //WHEN
        List<Pants> actual = pantsDao.findAll(0, 3);
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
        Pants expected = getPants();
        savePants(expected);
        //WHEN
        expected.setCost(777);
        expected.setMaker("TEST");
        pantsDao.update(expected);
        //THEN
        Pants actual = findPants(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        Pants expected = getPants();
        savePants(expected);
        //WHEN
        Pants actual = pantsDao.updateAvailable(expected, false);
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
        Pants expected = getPants();
        savePants(expected);
        //WHEN
        Pants actual = pantsDao.updateCost(expected, 777.0);
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
        Pants expected = getPants();
        savePants(expected);
        //WHEN
        pantsDao.delete(expected);
        //THEN
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<Mitten> actual = session.createQuery("select p from Pants as p ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        Pants pants1 = getPants();
        Pants pants2 = getPants();
        Pants pants3 = getPants();
        savePants(pants1);
        savePants(pants2);
        savePants(pants3);
        //WHEN
        long actual = pantsDao.count();
        //THEN
        assertEquals(3L, actual);
    }
    private Pants getPants() {
        AtomicLong counter = new AtomicLong(0);
        Pants pants = new Pants();
        pants.setProductName(ProductGroup.PANTS);
        pants.setMaker("QUIKSILVER");
        pants.setMembrane(15000);
        pants.setGender(Gender.FEMALE);
        pants.setAvailableToRental(true);
        pants.setCost(7.0);
        Image image = new Image();
        image.setImageName("imgPants" + counter.incrementAndGet() + ".png");
        image.addItem(pants);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("M");
        equipmentSize.addEquipment(pants);
        return pants;
    }

    private Pants findPants(UUID id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select p from Pants as p where id = :pants_id ");
        query.setParameter("pants_id", id);
        Pants actual = (Pants) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }

    private void savePants(Pants expected) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}
