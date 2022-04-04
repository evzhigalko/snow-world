package by.zhigalko.snow.world.dao.image;

import by.zhigalko.snow.world.entity.Image;
import by.zhigalko.snow.world.config.ApplicationConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@ContextConfiguration(classes = ApplicationConfig.class)
class ImageDaoImplTest {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ImageDao imageDao;

    @Test
    @Transactional
    @Rollback
    void saveImage() {
        Image expected = new Image();
        expected.setImageName("test.png");

        Session session = sessionFactory.getCurrentSession();
        session.save(expected);

        Image actual = session.find(Image.class, expected.getId());
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void deleteImage() {
        Image image = new Image();
        image.setImageName("test.png");
        Image expected = imageDao.saveImage(image);

        imageDao.deleteImage(expected);

        Session session = sessionFactory.getCurrentSession();
        Image actual = session.find(Image.class, expected.getId());
        assertNull(actual);
    }
}