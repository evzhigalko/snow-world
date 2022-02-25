package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.HardnessLevel;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.enums.RidingLevel;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.util.ApplicationConfig;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class SnowboardDaoImplTest {
    private static SnowboardDaoImpl snowboardDao;
    private static ApplicationContext context;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        snowboardDao = context.getBean("snowboardDao", SnowboardDaoImpl.class);
        sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
    }

    @BeforeEach
    void setUp() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete Snowboard");
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
        Snowboard expected = getSnowboard();
        //WHEN
        snowboardDao.save(expected);

        //THEN
        Snowboard actual = findSnowboard(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findByIdTest() {
        //GIVEN
        Snowboard expected = getSnowboard();
        saveSnowboard(expected);
        //WHEN
        Snowboard actual = snowboardDao.findById(expected.getId());

        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void findAllTest() {
        //THEN
        Snowboard snowboard1 = new Snowboard();
        snowboard1.setProductName(ProductGroup.SNOWBOARD);
        snowboard1.setMaker("NIDECKER");
        snowboard1.setGender(Gender.UNISEX);
        snowboard1.setRidingLevel(RidingLevel.MEDIUM);
        snowboard1.setHardnessLevel(HardnessLevel.SIX);
        snowboard1.setAvailableToRental(true);
        snowboard1.setCost(12.0);
        EquipmentSize equipmentSize1 = new EquipmentSize();
        equipmentSize1.setEquipmentSizeId("SN160");
        equipmentSize1.setUserMinHeight(175);
        equipmentSize1.setUserMaxHeight(195);
        equipmentSize1.setUserMinWeight(80);
        equipmentSize1.setUserMaxWeight(110);
        Image image1 = new Image();
        image1.setImageName("imgSNB1.png");
        image1.addImage(snowboard1);
        equipmentSize1.addEquipment(snowboard1);

        Snowboard snowboard2 = new Snowboard();
        snowboard2.setProductName(ProductGroup.SNOWBOARD);
        snowboard2.setMaker("NITRO");
        snowboard2.setGender(Gender.UNISEX);
        snowboard2.setRidingLevel(RidingLevel.BEGINNER);
        snowboard2.setHardnessLevel(HardnessLevel.SIX);
        snowboard2.setAvailableToRental(true);
        snowboard2.setCost(12.0);
        EquipmentSize equipmentSize2 = new EquipmentSize();
        equipmentSize2.setEquipmentSizeId("SN160");
        equipmentSize2.setUserMinHeight(175);
        equipmentSize2.setUserMaxHeight(195);
        equipmentSize2.setUserMinWeight(80);
        equipmentSize2.setUserMaxWeight(110);
        Image image2 = new Image();
        image2.setImageName("imgSNB2.png");
        image2.addImage(snowboard2);
        equipmentSize2.addEquipment(snowboard2);
        saveSnowboard(snowboard1);
        saveSnowboard(snowboard2);
        List<Snowboard> expected = List.of(snowboard1, snowboard2);

        //WHEN
        List<Snowboard> actual = snowboardDao.findAll(0,3);
        //THEN
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void updateTest() {
        //GIVEN
        Snowboard expected = getSnowboard();
        saveSnowboard(expected);
        //WHEN
        expected.setCost(77.0);
        expected.setMaker("TEST");
        snowboardDao.update(expected);

        //THEN
        Snowboard actual = findSnowboard(expected.getId());
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    @Test
    void updateAvailableTest() {
        //GIVEN
        Snowboard expected = getSnowboard();
        saveSnowboard(expected);

        //WHEN
        Snowboard actual = snowboardDao.updateAvailable(expected, false);

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
        Snowboard expected = getSnowboard();
        saveSnowboard(expected);
        expected.setCost(555.0);
        //WHEN
        Snowboard actual = snowboardDao.updateCost(expected, 50.0);

        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.getCost(),actual.getCost());
        assertEquals(expected, actual);
    }

    @Test
    void deleteTest() {
        //GIVEN
        Snowboard expected = getSnowboard();
        saveSnowboard(expected);
        //WHEN
        snowboardDao.delete(expected);
        //THEN
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<Snowboard> actual = session.createQuery("select snb from Snowboard as snb ").list();
        session.getTransaction().commit();
        session.close();
        assertEquals(0, actual.size());
    }

    @Test
    void countTest() {
        //THEN
        Snowboard snowboard1 = new Snowboard();
        snowboard1.setProductName(ProductGroup.SNOWBOARD);
        snowboard1.setMaker("NIDECKER");
        snowboard1.setGender(Gender.UNISEX);
        snowboard1.setRidingLevel(RidingLevel.MEDIUM);
        snowboard1.setHardnessLevel(HardnessLevel.SIX);
        snowboard1.setAvailableToRental(true);
        snowboard1.setCost(12.0);
        EquipmentSize equipmentSize1 = new EquipmentSize();
        equipmentSize1.setEquipmentSizeId("SN160");
        equipmentSize1.setUserMinHeight(175);
        equipmentSize1.setUserMaxHeight(195);
        equipmentSize1.setUserMinWeight(80);
        equipmentSize1.setUserMaxWeight(110);
        Image image1 = new Image();
        image1.setImageName("imgSNB1.png");
        image1.addImage(snowboard1);
        equipmentSize1.addEquipment(snowboard1);

        Snowboard snowboard2 = new Snowboard();
        snowboard2.setProductName(ProductGroup.SNOWBOARD);
        snowboard2.setMaker("NITRO");
        snowboard2.setGender(Gender.UNISEX);
        snowboard2.setRidingLevel(RidingLevel.BEGINNER);
        snowboard2.setHardnessLevel(HardnessLevel.SIX);
        snowboard2.setAvailableToRental(true);
        snowboard2.setCost(12.0);
        EquipmentSize equipmentSize2 = new EquipmentSize();
        equipmentSize2.setEquipmentSizeId("SN160");
        equipmentSize2.setUserMinHeight(175);
        equipmentSize2.setUserMaxHeight(195);
        equipmentSize2.setUserMinWeight(80);
        equipmentSize2.setUserMaxWeight(110);
        Image image2 = new Image();
        image2.setImageName("imgSNB2.png");
        image2.addImage(snowboard2);
        equipmentSize2.addEquipment(snowboard2);
        saveSnowboard(snowboard1);
        saveSnowboard(snowboard2);

        //WHEN
        long actual = snowboardDao.count();

        //THEN
        assertEquals(2L, actual);
    }
    @Test
    void findAllSizesTest() {
        //GIVEN
        EquipmentSize equipmentSize1 = new EquipmentSize();
        equipmentSize1.setEquipmentSizeId("SN148");
        equipmentSize1.setUserMinHeight(165);
        equipmentSize1.setUserMaxHeight(175);
        equipmentSize1.setUserMinWeight(55);
        equipmentSize1.setUserMaxWeight(75);
        EquipmentSize equipmentSize2 = new EquipmentSize();
        equipmentSize2.setEquipmentSizeId("SN160");
        equipmentSize2.setUserMinHeight(175);
        equipmentSize2.setUserMaxHeight(195);
        equipmentSize2.setUserMinWeight(80);
        equipmentSize2.setUserMaxWeight(110);
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(equipmentSize1);
        session.save(equipmentSize2);
        session.getTransaction().commit();
        session.close();
        List<EquipmentSize> expected = List.of(equipmentSize1, equipmentSize2);

        //WHEN
        List<EquipmentSize> actual = snowboardDao.findAllSizes();
        //THEN
        assertNotNull(actual);
        assertEquals(expected.get(0),actual.get(0));
        assertEquals(expected.get(0).getUserMinHeight(),actual.get(0).getUserMinHeight());
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected, actual);
    }

    private void saveSnowboard(Snowboard expected) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
    }

    @NotNull
    private Snowboard getSnowboard() {
        Snowboard expected = new Snowboard();
        expected.setProductName(ProductGroup.SNOWBOARD);
        expected.setMaker("NIDECKER");
        expected.setGender(Gender.UNISEX);
        expected.setRidingLevel(RidingLevel.MEDIUM);
        expected.setHardnessLevel(HardnessLevel.SIX);
        expected.setAvailableToRental(true);
        expected.setCost(12.0);
        EquipmentSize equipmentSize = new EquipmentSize();
        equipmentSize.setEquipmentSizeId("SN160");
        equipmentSize.setUserMinHeight(175);
        equipmentSize.setUserMaxHeight(195);
        equipmentSize.setUserMinWeight(80);
        equipmentSize.setUserMaxWeight(110);
        Image image = new Image();
        image.setImageName("imgSNB1.png");
        image.addImage(expected);
        equipmentSize.addEquipment(expected);
        return expected;
    }

    private Snowboard findSnowboard(UUID id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select snb from Snowboard as snb where id = :snowboard_id ");
        query.setParameter("snowboard_id", id);
        Snowboard actual = (Snowboard) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }
}