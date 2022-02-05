package by.zhigalko.snow.world.dal.dao.factory;

import by.zhigalko.snow.world.dal.dao.BaseDaoEquipmentImpl;
import by.zhigalko.snow.world.dal.dao.snowboard.SnowboardDaoImpl;
import by.zhigalko.snow.world.dal.entity.enums.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaoEquipmentFactoryImplTest {
    private static DaoEquipmentFactory daoFactory;

    @BeforeAll
    static void init() {
        daoFactory = new DaoEquipmentFactoryImpl();
    }

    @Test
    void getDaoTest() {
        //GIVEN
        Page page = Page.SNOWBOARD_LIST;
        //WHEN
        BaseDaoEquipmentImpl actual = daoFactory.getDao(page);
        //THEN
        assertNotNull(actual);
        assertEquals(SnowboardDaoImpl.getInstance().hashCode(),actual.hashCode());
        assertEquals(SnowboardDaoImpl.getInstance(),actual);
    }
}