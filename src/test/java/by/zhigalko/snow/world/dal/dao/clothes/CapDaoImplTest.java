package by.zhigalko.snow.world.dal.dao.clothes;

import by.zhigalko.snow.world.dal.entity.EquipmentSize;
import by.zhigalko.snow.world.dal.entity.Image;
import by.zhigalko.snow.world.dal.entity.clothes.Cap;
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
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import static org.junit.jupiter.api.Assertions.*;

class CapDaoImplTest {
    private static CapDaoImpl capDao;
    @BeforeAll
    static void init() {
        capDao = CapDaoImpl.getInstance();
    }

    @BeforeEach
    void setUp() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from Cap where true");
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
        Cap expected = getCap();
        //WHEN
        capDao.save(expected);
        //THEN
        Cap actual = findCap(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        Cap expected = getCap();
        saveCap(expected);
        //WHEN
        Cap actual = capDao.findById(expected.getId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //GIVEN
        Cap cap1 = getCap();
        Cap cap2 = getCap();
        Cap cap3 = getCap();
        saveCap(cap1);
        saveCap(cap2);
        saveCap(cap3);
        List<Cap> expected = List.of(cap1, cap2, cap3);
        //WHEN
        List<Cap> actual = capDao.findAll(0, 3);
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
        Cap expected = getCap();
        saveCap(expected);
        //WHEN
        expected.setCost(777);
        expected.setMaker("TEST");
        capDao.update(expected);
        //THEN
        Cap actual = findCap(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        Cap expected = getCap();
        saveCap(expected);
        //WHEN
        Cap actual = capDao.updateAvailable(expected, false);
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
        Cap expected = getCap();
        saveCap(expected);
        //WHEN
        Cap actual = capDao.updateCost(expected, 777.0);
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
        Cap expected = getCap();
        saveCap(expected);
        //WHEN
        capDao.delete(expected);
        //THEN
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        List<Cap> actual = session.createQuery("select cap from Cap as cap ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        Cap cap1 = getCap();
        Cap cap2 = getCap();
        Cap cap3 = getCap();
        saveCap(cap1);
        saveCap(cap2);
        saveCap(cap3);
        //WHEN
        long actual = capDao.count();
        //THEN
        assertEquals(3L, actual);
    }
    private Cap getCap() {
        AtomicLong counter = new AtomicLong(0);
        Cap cap = new Cap();
        cap.setProductName(ProductGroup.CAP);
        cap.setMaker("DC SHOES");
        cap.setGender(Gender.UNISEX);
        cap.setAvailableToRental(true);
        cap.setCost(2.0);
        Image image = new Image();
        image.setImageName("imgCap" + counter.incrementAndGet() + ".png");
        image.addImage(cap);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("XS");
        equipmentSize.addEquipment(cap);
        return cap;
    }

    private Cap findCap(UUID id) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select cap from Cap AS cap where id = :cap_id ");
        query.setParameter("cap_id", id);
        Cap actual = (Cap) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }

    private void saveCap(Cap expected) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}