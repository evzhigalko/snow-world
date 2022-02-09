package by.zhigalko.snow.world.dao;

import by.zhigalko.snow.world.dao.item.equipment_size.EquipmentSizeDao;
import by.zhigalko.snow.world.dao.item.equipment_size.EquipmentSizeDaoImpl;
import by.zhigalko.snow.world.entity.EquipmentSize;
import by.zhigalko.snow.world.service.SessionManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentSizeDaoImplTest {
    private static EquipmentSizeDao equipmentSizeDao;

    @BeforeAll
    static void init() {
        equipmentSizeDao = EquipmentSizeDaoImpl.getInstance();
    }

    @BeforeEach
    void setUp() {
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM EquipmentSize WHERE TRUE");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @AfterAll
    static void closeSession() {
        SessionManager.closeSession();
        SessionManager.closeSessionFactory();
    }

    @Test
    void findByIdTest() {
        //GIVEN
        EquipmentSize expected = new EquipmentSize();
        expected.setEquipmentSizeId("SN160");
        expected.setUserMinHeight(175);
        expected.setUserMaxHeight(195);
        expected.setUserMinWeight(80);
        expected.setUserMaxWeight(110);
        Session session = SessionManager.getSession();
        session.getTransaction().begin();
        session.save(expected);
        session.getTransaction().commit();
        session.close();
        //WHEN
        EquipmentSize actual = equipmentSizeDao.findById(expected.getEquipmentSizeId());
        //THEN
        assertNotNull(actual);
        assertNotNull(actual.getEquipmentSizeId());
        assertEquals(expected, actual);
    }
}