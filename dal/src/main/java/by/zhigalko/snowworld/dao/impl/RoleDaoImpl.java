package by.zhigalko.snowworld.dao.impl;

import by.zhigalko.snowworld.dao.RoleDao;
import by.zhigalko.snowworld.entity.Role;
import by.zhigalko.snowworld.model.RoleName;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("roleDao")
@Transactional
public class RoleDaoImpl extends BaseDaoSaveEntityImpl<Role> implements RoleDao {
    @Override
    public Role find(RoleName roleName) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteria = cb.createQuery(Role.class);
        Root<Role> roleRoot = criteria.from(Role.class);
        criteria.select(roleRoot)
                .where(cb.equal(roleRoot.get("roleName"), roleName));
        return session.createQuery(criteria).getSingleResult();
    }
}
