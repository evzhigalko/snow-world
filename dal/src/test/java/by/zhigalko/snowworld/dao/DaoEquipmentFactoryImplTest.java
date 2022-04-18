package by.zhigalko.snowworld.dao;

import by.zhigalko.snowworld.dao.impl.DaoEquipmentFactoryImpl;
import by.zhigalko.snowworld.dao.impl.BaseDaoItemImpl;
import by.zhigalko.snowworld.dao.impl.SnowboardDaoImpl;
import by.zhigalko.snowworld.model.Page;
import by.zhigalko.snowworld.config.ApplicationConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.jupiter.api.Assertions.*;

class DaoEquipmentFactoryImplTest {
    private static DaoEquipmentFactory daoFactory;
    private static ApplicationContext context;
    private static SnowboardDaoImpl snowboardDao;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        snowboardDao = context.getBean("snowboardDao", SnowboardDaoImpl.class);
        daoFactory = context.getBean("daoEquipmentFactory", DaoEquipmentFactoryImpl.class);
    }

    @Test
    void getDaoTest() {
        //GIVEN
        Page page = Page.SNOWBOARD_LIST;
        //WHEN
        BaseDaoItemImpl actual = daoFactory.getDao(page);
        //THEN
        assertNotNull(actual);
        assertEquals(snowboardDao.hashCode(),actual.hashCode());
        assertEquals(snowboardDao,actual);
    }
    @Test
    void getAllSizesDaoTest() {
        //GIVEN
        String item = "snowboard";
        //WHEN
        EquipmentAllSizesDao actual = daoFactory.getAllSizesDao(item);
        //GIVEN
        assertNotNull(actual);
        assertEquals(snowboardDao.hashCode(),actual.hashCode());
        assertEquals(snowboardDao,actual);
    }
}