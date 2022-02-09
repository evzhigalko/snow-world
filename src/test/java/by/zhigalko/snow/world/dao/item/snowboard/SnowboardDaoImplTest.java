package by.zhigalko.snow.world.dao.item.snowboard;

import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.entity.enums.Gender;
import by.zhigalko.snow.world.entity.enums.HardnessLevel;
import by.zhigalko.snow.world.entity.enums.ProductGroup;
import by.zhigalko.snow.world.entity.enums.RidingLevel;
import by.zhigalko.snow.world.entity.snowboard.Snowboard;
import by.zhigalko.snow.world.service.SessionManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SnowboardDaoImplTest {
    private static SnowboardDaoImpl snowboardDao;

    @BeforeAll
    static void init() {
        snowboardDao = SnowboardDaoImpl.getInstance();
    }

    @BeforeEach
    void setUp() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM Snowboard WHERE TRUE");
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
        Session session2 = SessionManager.getSession();
        session2.getTransaction().begin();
        List<Snowboard> actual = session2.createQuery("select snb from Snowboard as snb ").list();
        session2.getTransaction().commit();
        session2.close();
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

    private void saveSnowboard(Snowboard expected) {
        Session session = SessionManager.getSession();
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
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("select snb from Snowboard as snb where id = :snowboard_id ");
        query.setParameter("snowboard_id", id);
        Snowboard actual = (Snowboard) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return actual;
    }
}