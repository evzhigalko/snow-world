package by.zhigalko.snow.world.dao.item.factory;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.dao.item.snowboard.SnowboardDaoImpl;
import by.zhigalko.snow.world.entity.enums.Page;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaoEquipmentFactoryImplTest {
    private static DaoEquipmentFactory daoFactory;

    @BeforeAll
    static void init() {
        daoFactory = DaoEquipmentFactoryImpl.getInstance();
    }

    @Test
    void getDaoTest() {
        //GIVEN
        Page page = Page.SNOWBOARD_LIST;
        //WHEN
        BaseDaoItemImpl actual = daoFactory.getDao(page);
        //THEN
        assertNotNull(actual);
        assertEquals(SnowboardDaoImpl.getInstance().hashCode(),actual.hashCode());
        assertEquals(SnowboardDaoImpl.getInstance(),actual);
    }
}