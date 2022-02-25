//package by.zhigalko.snow.world.dao.user;
//
//import by.zhigalko.snow.world.entity.Role;
//import by.zhigalko.snow.world.entity.enums.RoleName;
//import by.zhigalko.snow.world.util.ApplicationConfig;
//
//import javax.persistence.Query;
//import org.hibernate.Session;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import static org.junit.jupiter.api.Assertions.*;
//
//class RoleDaoImplTest {
//    private static RoleDaoImpl roleDao;
//    private static ApplicationContext context;
//
//    @BeforeAll
//    static void init() {
//        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        roleDao = context.getBean("roleDao", RoleDaoImpl.class);
//    }
//
//    @BeforeEach
//    void setUp() {
//        Session session = SessionManager.getSession();
//        session.getTransaction().begin();
//        Query query = session.createQuery("delete from Role where true");
//        query.executeUpdate();
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    @AfterAll
//    static void closeSession() {
//        SessionManager.closeSessionFactory();
//    }
//
//    @Test
//    void saveTest() {
//        //GIVEN
//        Role expected = new Role();
//        expected.setRoleName(RoleName.ADMIN);
//        //WHEN
//        boolean isSaved = roleDao.save(expected);
//        //THEN
//        if (isSaved) {
//            Session session = SessionManager.getSession();
//            session.getTransaction().begin();
//            Role actual = session.find(Role.class, expected.getId());
//            session.getTransaction().commit();
//            assertNotNull(actual);
//            assertNotNull(actual.getId());
//            assertEquals(expected.getId(), actual.getId());
//            assertEquals(expected, actual);
//        }
//    }
//
//    @Test
//    void findTest() {
//        //GIVEN
//        Role expected = new Role();
//        expected.setRoleName(RoleName.ADMIN);
//        Session session = SessionManager.getSession();
//        session.getTransaction().begin();
//        session.save(expected);
//        session.getTransaction().commit();
//        //WHEN
//        Role actual = roleDao.find(expected.getRoleName());
//        //THEN
//        assertNotNull(actual);
//        assertEquals(expected.getId(), actual.getId());
//        assertEquals(RoleName.ADMIN, actual.getRoleName());
//        assertEquals(expected.getRoleName(), actual.getRoleName());
//        assertEquals(expected, actual);
//    }
//}
