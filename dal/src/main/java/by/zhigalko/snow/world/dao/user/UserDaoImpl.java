package by.zhigalko.snow.world.dao.user;

import by.zhigalko.snow.world.dao.BaseDaoSaveEntityImpl;
import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.entity.enums.RoleName;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends BaseDaoSaveEntityImpl<User> implements UserDao {
    @Override
    public User findByUsernameAndPassword(String username, String password) throws NoResultException {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot)
                .where(cb.and(
                        cb.equal(userRoot.get("username"), username),
                        cb.equal(userRoot.get("password"), password)
                ));
        return session.createQuery(criteria).getSingleResult();
    }

    @Override
    public boolean findByUsernameAndEmail(String username, String email) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot)
                .where(cb.or(
                        cb.equal(userRoot.get("username"), username),
                        cb.equal(userRoot.get("email"), email)
                ));
        return session.createQuery(criteria)
                .getResultStream()
                .anyMatch(Objects::nonNull);
    }

    @Override
    public List<User> findAllUsers() {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        Join<User, Role> userRole = userRoot.join("role");
        Path<Role> userRoleName = userRole.get("roleName");
        criteria.select(userRoot)
                .where(cb.equal(userRoleName, RoleName.USER));
        return session.createQuery(criteria).getResultList();
    }
}
