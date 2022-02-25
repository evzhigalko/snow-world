//package by.zhigalko.snow.world.dao.user;
//
//import by.zhigalko.snow.world.entity.Role;
//import by.zhigalko.snow.world.entity.User;
//import by.zhigalko.snow.world.entity.enums.Gender;
//import by.zhigalko.snow.world.entity.enums.RoleName;
//import by.zhigalko.snow.world.util.ApplicationConfig;
//
//import javax.persistence.NoResultException;
//import javax.persistence.Query;
//import javax.persistence.RollbackException;
//import org.hibernate.Session;
//import org.jetbrains.annotations.NotNull;
//import org.junit.jupiter.api.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserDaoImplTest {
//    private static UserDaoImpl userDao;
//    private static ApplicationContext context;
//
//    @BeforeAll
//    static void init() {
//        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        userDao = context.getBean("userDao", UserDaoImpl.class);
//    }
//
//    @BeforeEach
//    void setUp() {
//        Session session = SessionManager.getSession();
//        session.getTransaction().begin();
//        Query query = session.createQuery("delete from User where true");
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
//        User expected = getUser();
//        //WHEN
//        boolean isSaved = userDao.save(expected);
//        //THEN
//        if (isSaved) {
//            Session session = SessionManager.getSession();
//            session.getTransaction().begin();
//            User actual = session.find(User.class, expected.getId());
//            session.getTransaction().commit();
//            assertNotNull(actual);
//            assertEquals(expected.getId(), actual.getId());
//            assertEquals(expected.getUsername(), actual.getUsername());
//            assertEquals(expected.getRole().hashCode(), actual.getRole().hashCode());
//            assertEquals(expected, actual);
//        }
//    }
//
//    @Test
//    void findByUsernameAndPassword() {
//        //GIVEN
//        User expected = getUser();
//        saveUser(expected);
//        //WHEN
//        User actual = null;
//        try {
//            actual = userDao.findByUsernameAndPassword("admin", "qwerty");
//        } catch (NoResultException e) {
//            e.printStackTrace();
//        }
//        //THEN
//        assertNotNull(actual);
//        assertNotNull(actual.getId());
//        assertEquals(RoleName.USER, actual.getRole().getRoleName());
//        assertEquals(expected.getRole().hashCode(), actual.getRole().hashCode());
//        assertThrows(NoResultException.class, ()-> userDao.findByUsernameAndPassword("test", "test"));
//        assertEquals(expected.hashCode(), actual.hashCode());
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void findByUsernameAndEmailTest() {
//        //GIVEN
//        User user = getUser();
//        boolean expected = saveUser(user);
//        //WHEN
//        boolean actual = userDao.findByUsernameAndEmail(user.getUsername(), user.getEmail());
//        //THEN
//        assertTrue(actual);
//        Assertions.assertDoesNotThrow(()-> userDao.findByUsernameAndEmail("test", "test"));
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void findAll() {
//        //GIVEN
//        User user1 = getUser();
//        User user2 = getUser();
//        User user3 = getUser();
//        Role role = new Role();
//        role.setRoleName(RoleName.ADMIN);
//        role.addUser(user3);
//        saveUser(user1);
//        saveUser(user2);
//        saveUser(user3);
//        List<User> expected = List.of(user1, user2, user3);
//        //WHEN
//        List<User> actual = userDao.findAllUsers();
//        //THEN
//        assertNotNull(actual);
//        assertEquals(2, actual.size());
//        assertEquals(RoleName.USER, actual.get(0).getRole().getRoleName());
//        assertEquals(RoleName.USER, actual.get(1).getRole().getRoleName());
//        assertEquals(expected.get(2).getRole().getRoleName(), RoleName.ADMIN);
//        Assertions.assertDoesNotThrow(()-> userDao.findAllUsers());
//    }
//
//    @NotNull
//    private User getUser() {
//        Role role = new Role();
//        role.setRoleName(RoleName.USER);
//        User expected = new User();
//        expected.setUsername("admin");
//        expected.setPassword("qwerty");
//        expected.setPhoneNumber("+375291212121");
//        expected.setFirstName("Evgeniy");
//        expected.setLastName("Zhigalko");
//        role.addUser(expected);
//        expected.setGender(Gender.MALE);
//        expected.setEmail("evgeniy.zhigalko@gmail.com");
//        return expected;
//    }
//
//    private boolean saveUser(User expected) {
//        Session session = SessionManager.getSession();
//        try {
//            session.getTransaction().begin();
//            session.save(expected);
//            session.getTransaction().commit();
//        } catch (RollbackException e) {
//            session.getTransaction().rollback();
//            return false;
//        }
//        session.close();
//        return true;
//    }
//}
