package by.zhigalko.snow.world.dao.item.clothes;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.clothes.Cap;
import by.zhigalko.snow.world.entity.clothes.Mask;
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

class MaskDaoImplTest {
    private static MaskDaoImpl maskDao;
    private static ApplicationContext context;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        maskDao = context.getBean("maskDao", MaskDaoImpl.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @BeforeEach
    void setUp() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from Mask");
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
        Mask expected = getMask();
        //WHEN
        maskDao.save(expected);
        //THEN
        Mask actual = findMask(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        Mask expected = getMask();
        saveMask(expected);
        //WHEN
        Mask actual = maskDao.findById(expected.getId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //GIVEN
        Mask mask1 = getMask();
        Mask mask2 = getMask();
        Mask mask3 = getMask();
        saveMask(mask1);
        saveMask(mask2);
        saveMask(mask3);
        List<Mask> expected = List.of(mask1, mask2, mask3);
        //WHEN
        List<Mask> actual = maskDao.findAll(0, 3);
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
        Mask expected = getMask();
        saveMask(expected);
        //WHEN
        expected.setCost(777);
        expected.setMaker("TEST");
        maskDao.update(expected);
        //THEN
        Mask actual = findMask(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        Mask expected = getMask();
        saveMask(expected);
        //WHEN
        Mask actual = maskDao.updateAvailable(expected, false);
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
        Mask expected = getMask();
        saveMask(expected);
        //WHEN
        Mask actual = maskDao.updateCost(expected, 777.0);
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
        Mask expected = getMask();
        saveMask(expected);
        //WHEN
        maskDao.delete(expected);
        //THEN
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<Cap> actual = session.createQuery("select m from Mask as m ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        Mask mask1 = getMask();
        Mask mask2 = getMask();
        Mask mask3 = getMask();
        saveMask(mask1);
        saveMask(mask2);
        saveMask(mask3);
        //WHEN
        long actual = maskDao.count();
        //THEN
        assertEquals(3L, actual);
    }
    private Mask getMask() {
        AtomicLong counter = new AtomicLong(0);
        Mask mask = new Mask();
        mask.setProductName(Product.MASK);
        mask.setMaker("PRIME");
        mask.setGender(Gender.UNISEX);
        mask.setAvailableToRental(true);
        mask.setCost(2.0);
        Image image = new Image();
        image.setImageName("imgMask" + counter.incrementAndGet() + ".png");
        image.addItem(mask);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("XS");
        equipmentSize.addEquipment(mask);
        return mask;
    }

    private Mask findMask(UUID id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select m from Mask as m where id = :mask_id ");
        query.setParameter("mask_id", id);
        Mask actual = (Mask) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }

    private void saveMask(Mask expected) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}
