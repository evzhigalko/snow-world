package by.zhigalko.snow.world.dal.dao.ski;

import by.zhigalko.snow.world.dal.entity.EquipmentSize;
import by.zhigalko.snow.world.dal.entity.Image;
import by.zhigalko.snow.world.dal.entity.enums.Gender;
import by.zhigalko.snow.world.dal.entity.enums.ProductGroup;
import by.zhigalko.snow.world.dal.entity.ski.SkiBoot;
import by.zhigalko.snow.world.dal.entity.snowboard.Snowboard;
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

class SkiBootDaoImplTest {
    private static SkiBootDaoImpl skiBootDao;

    @BeforeAll
    static void init() {
        skiBootDao = SkiBootDaoImpl.getInstance();
    }

    @BeforeEach
    void setUp() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from SkiBoot where true");
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
        SkiBoot expected = getSkiBoot();
        //WHEN
        skiBootDao.save(expected);
        //THEN
        SkiBoot actual = findSkiBoot(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        SkiBoot expected = getSkiBoot();
        saveSkiBoot(expected);
        //WHEN
        SkiBoot actual = skiBootDao.findById(expected.getId());
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
        SkiBoot skiBoot1 = getSkiBoot();
        SkiBoot skiBoot2 = getSkiBoot();
        SkiBoot skiBoot3 = getSkiBoot();
        saveSkiBoot(skiBoot1);
        saveSkiBoot(skiBoot2);
        saveSkiBoot(skiBoot3);
        List<SkiBoot> expected = List.of(skiBoot1, skiBoot2, skiBoot3);
        //WHEN
        List<SkiBoot> actual = skiBootDao.findAll(0, 3);
        //THEN
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(3, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    void updateTest() {
        //GIVEN
        SkiBoot expected = getSkiBoot();
        saveSkiBoot(expected);
        //WHEN
        expected.setCost(777);
        expected.setMaker("TEST");
        skiBootDao.update(expected);
        //THEN
        SkiBoot actual = findSkiBoot(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    void deleteTest() {
        //GIVEN
        SkiBoot expected = getSkiBoot();
        saveSkiBoot(expected);
        //WHEN
        skiBootDao.delete(expected);
        //THEN
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        List<Snowboard> actual = session.createQuery("select skb from SkiBoot as skb ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        SkiBoot skiBoot1 = getSkiBoot();
        SkiBoot skiBoot2 = getSkiBoot();
        SkiBoot skiBoot3 = getSkiBoot();
        saveSkiBoot(skiBoot1);
        saveSkiBoot(skiBoot2);
        saveSkiBoot(skiBoot3);
        //WHEN
        long actual = skiBootDao.count();
        //THEN
        assertEquals(3L, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        SkiBoot expected = getSkiBoot();
        saveSkiBoot(expected);
        //WHEN
        SkiBoot actual = skiBootDao.updateAvailable(expected, false);
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
        SkiBoot expected = getSkiBoot();
        saveSkiBoot(expected);
        //WHEN
        SkiBoot actual = skiBootDao.updateCost(expected, 777.0);
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.hashCode(),actual.hashCode());
        assertEquals(expected.getCost(),actual.getCost());
        assertEquals(expected, actual);
    }

    private SkiBoot getSkiBoot() {
        AtomicLong counter = new AtomicLong(0);
        SkiBoot skiBoot = new SkiBoot();
        skiBoot.setProductName(ProductGroup.SKI_BOOT);
        skiBoot.setMaker("Atomic");
        skiBoot.setGender(Gender.MALE);
        skiBoot.setAvailableToRental(true);
        skiBoot.setCost(7.0);
        Image image = new Image();
        image.setImageName("imgSkiBoot" + counter.incrementAndGet() + ".png");
        image.addImage(skiBoot);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("43");
        equipmentSize.addEquipment(skiBoot);
        return skiBoot;
    }

    private SkiBoot findSkiBoot(UUID id) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select skb from SkiBoot AS skb where id = :ski_boot_id ");
        query.setParameter("ski_boot_id", id);
        SkiBoot actual = (SkiBoot) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }

    private void saveSkiBoot(SkiBoot expected) {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}
