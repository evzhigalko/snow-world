package by.zhigalko.snowworld.repository;

import by.zhigalko.snowworld.entity.Role;
import by.zhigalko.snowworld.model.RoleName;
import by.zhigalko.snowworld.config.ApplicationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@Rollback
@Transactional
class RoleRepositoryTest {
    private Role expected;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    private EntityManager entityManager;

    @BeforeEach
    void setUp(){
        entityManager = entityManagerFactoryBean.getNativeEntityManagerFactory().createEntityManager();
        TypedQuery<Role> query = entityManager.createQuery("select r from Role as r where roleName = :roleName", Role.class);
        query.setParameter("roleName", RoleName.USER);
        expected = query.getSingleResult();
    }

    @Test
    void findByRoleNameTest() {
        Role actual = roleRepository.findByRoleName(RoleName.USER);

        assertNotNull(actual);
        Assertions.assertEquals(expected.getRoleName(), actual.getRoleName());
        assertEquals(expected, actual);
        System.out.println(expected);
        System.out.println(actual);
    }
}
