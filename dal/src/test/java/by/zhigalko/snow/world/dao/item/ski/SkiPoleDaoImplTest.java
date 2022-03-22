package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.entity.Equipment;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.Product;
import by.zhigalko.snow.world.entity.ski.SkiPole;
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
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

class SkiPoleDaoImplTest {
    private static SkiPoleDaoImpl skiPoleDao;
    private static ApplicationContext context;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        skiPoleDao = context.getBean("skiPoleDao", SkiPoleDaoImpl.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @BeforeEach
    void setUp() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from SkiPole");
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @AfterAll
    static void closeSession() {
        sessionFactory.close();
    }

    @Test
    void saveTest() {
        //GIVEN
        SkiPole expected = getSkiPole();
        //WHEN
        skiPoleDao.save(expected);
        //THEN
        SkiPole actual = findSkiPole(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        SkiPole expected = getSkiPole();
        saveSkiPole(expected);
        //WHEN
        SkiPole actual = skiPoleDao.findById(expected.getId());
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
        SkiPole skiPole1 = getSkiPole();
        SkiPole skiPole2 = getSkiPole();
        SkiPole skiPole3 = getSkiPole();
        saveSkiPole(skiPole1);
        saveSkiPole(skiPole2);
        saveSkiPole(skiPole3);
        List<SkiPole> expected = List.of(skiPole1, skiPole2, skiPole3);
        //WHEN
        List<SkiPole> actual = skiPoleDao.findAll(0, 3);
        //THEN
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void updateTest() {
        //GIVEN
        SkiPole expected = getSkiPole();
        saveSkiPole(expected);
        //WHEN
        expected.setCost(77.0);
        expected.setMaker("TEST");
        skiPoleDao.update(expected);
        //THEN
        SkiPole actual = findSkiPole(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void deleteTest() {
        //GIVEN
        SkiPole expected = getSkiPole();
        saveSkiPole(expected);
        //WHEN
        skiPoleDao.delete(expected);
        //THEN
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<SkiPole> actual = session.createQuery("select s from SkiPole as s").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //GIVEN
        SkiPole skiPole1 = getSkiPole();
        SkiPole skiPole2 = getSkiPole();
        SkiPole skiPole3 = getSkiPole();
        saveSkiPole(skiPole1);
        saveSkiPole(skiPole2);
        saveSkiPole(skiPole3);
        //WHEN
        long actual = skiPoleDao.count();
        //THEN
        assertEquals(3L, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        SkiPole expected = getSkiPole();
        saveSkiPole(expected);
        //WHEN
        SkiPole actual = skiPoleDao.updateAvailable(expected, false);
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
        SkiPole expected = getSkiPole();
        saveSkiPole(expected);
        //WHEN
        SkiPole actual = skiPoleDao.updateCost(expected, 50.0);
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.getCost(),actual.getCost());
        assertEquals(expected, actual);
    }

    @Test
    void findAllSizesTest() {
        //GIVEN
        SkiPole skiPole1 = getSkiPole();
        SkiPole skiPole2 = getSkiPole();
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("SP125");
        equipmentSize.setUserMinHeight(174);
        equipmentSize.setUserMaxHeight(181);
        equipmentSize.addEquipment(skiPole2);
        saveSkiPole(skiPole1);
        saveSkiPole(skiPole2);
        List<SkiPole> list = List.of(skiPole1, skiPole2);
        List<EquipmentSize> expected = list.stream()
                .map(Equipment::getEquipmentSizeId)
                .collect(Collectors.toList());
        //WHEN
        List<EquipmentSize> actual = skiPoleDao.findAllSizes();
        //THEN
        assertNotNull(actual);
        assertEquals(expected.get(1).getEquipmentSizeId(), actual.get(1).getEquipmentSizeId());
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }


    private SkiPole getSkiPole() {
        SkiPole skiPole = new SkiPole();
        skiPole.setProductName(Product.SKI_POLE);
        skiPole.setMaker("FISCHER");
        skiPole.setGender(Gender.UNISEX);
        skiPole.setAvailableToRental(true);
        skiPole.setCost(10.0);
        Image image = new Image();
        image.setImageName("imgSkiPole.png");
        image.addItem(skiPole);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("SP110");
        equipmentSize.setUserMinHeight(153);
        equipmentSize.setUserMaxHeight(160);
        equipmentSize.addEquipment(skiPole);
        return skiPole;
    }

    private SkiPole findSkiPole(UUID id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select s from SkiPole AS s where id = :ski_pole_id ");
        query.setParameter("ski_pole_id", id);
        SkiPole actual = (SkiPole) query.getSingleResult();
        session.getTransaction().commit();
        return actual;
    }

    private void saveSkiPole(SkiPole expected) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }
}