package by.zhigalko.snow.world.dao.impl;

import by.zhigalko.snow.world.dao.ImageDao;
import by.zhigalko.snow.world.entity.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("imageDao")
@Transactional
public class ImageDaoImpl implements ImageDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ImageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Image saveImage(Image image) {
        Session session = sessionFactory.getCurrentSession();
        session.save(image);
        return image;
    }

    @Override
    public void deleteImage(Image image) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(image);
    }
}
