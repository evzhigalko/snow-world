package by.zhigalko.snow.world.dao.user;

import by.zhigalko.snow.world.dao.BaseDaoSaveEntityImpl;
import by.zhigalko.snow.world.entity.Role;
import by.zhigalko.snow.world.entity.enums.RoleName;
import by.zhigalko.snow.world.service.SessionManager;
import jakarta.persistence.RollbackException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

public class RoleDaoImpl extends BaseDaoSaveEntityImpl<Role> implements RoleDao{
    private static volatile RoleDaoImpl instance = null;

    private RoleDaoImpl() {}

    public static RoleDaoImpl getInstance() {
        if (instance == null) {
            synchronized (RoleDaoImpl.class) {
                if (instance == null) {
                    instance = new RoleDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Role find(RoleName roleName) {
        Session session = SessionManager.getSession();
        Role role = null;
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Role> criteria = cb.createQuery(Role.class);
            Root<Role> roleRoot = criteria.from(Role.class);
            criteria.select(roleRoot)
                            .where(cb.equal(roleRoot.get("roleName"),roleName));
            session.getTransaction().begin();
            role = session.createQuery(criteria).getSingleResult();
            session.getTransaction().commit();
        } catch (RollbackException e) {
            session.getTransaction().rollback();
        }
        session.close();
        return role;
    }
}
