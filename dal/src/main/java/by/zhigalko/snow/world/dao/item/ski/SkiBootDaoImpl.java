package by.zhigalko.snow.world.dao.item.ski;

import by.zhigalko.snow.world.dao.item.BaseDaoItemImpl;
import by.zhigalko.snow.world.entity.ski.SkiBoot;
import org.springframework.stereotype.Repository;

@Repository("skiBootDao")
public class SkiBootDaoImpl extends BaseDaoItemImpl<SkiBoot> {
    public SkiBootDaoImpl() {
        super(SkiBoot.class);
    }
}
