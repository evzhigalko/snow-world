package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.ski.SkiHelmet;
import org.springframework.stereotype.Repository;

@Repository("skiHelmetDao")
public class SkiHelmetDaoImpl extends BaseDaoItemImpl<SkiHelmet> {
    public SkiHelmetDaoImpl() {
        super(SkiHelmet.class);
    }
}
