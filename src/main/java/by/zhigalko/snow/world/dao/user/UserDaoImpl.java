package by.zhigalko.snow.world.dao.user;

import by.zhigalko.snow.world.dao.BaseDaoSaveEntityImpl;
import by.zhigalko.snow.world.entity.User;
import by.zhigalko.snow.world.service.SessionManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.RollbackException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import java.util.Objects;

public class UserDaoImpl extends BaseDaoSaveEntityImpl<User> implements UserDao{
    private static volatile UserDaoImpl instance = null;

    private UserDaoImpl() {}

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws NoResultException {
        Session session = SessionManager.getSession();
        User user = null;
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = cb.createQuery(User.class);
            Root<User> userRoot = criteria.from(User.class);
            criteria.select(userRoot)
                    .where(cb.and(
                            cb.equal(userRoot.get("username"), username),
                            cb.equal(userRoot.get("password"), password)
                    ));
            session.getTransaction().begin();
            user = session.createQuery(criteria).getSingleResult();
            session.getTransaction().commit();
        } catch (RollbackException e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    @Override
    public boolean findByUsernameAndEmail(String username, String email) {
        Session session = SessionManager.getSession();
        boolean userExists = false;
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = cb.createQuery(User.class);
            Root<User> userRoot = criteria.from(User.class);
            criteria.select(userRoot)
                    .where(cb.or(
                            cb.equal(userRoot.get("username"), username),
                            cb.equal(userRoot.get("email"), email)
                    ));
            session.getTransaction().begin();
            userExists = session.createQuery(criteria)
                    .getResultStream()
                    .anyMatch(Objects::nonNull);
            session.getTransaction().commit();
        } catch (RollbackException e) {
            session.getTransaction().rollback();
        }
        return userExists;
    }
}
