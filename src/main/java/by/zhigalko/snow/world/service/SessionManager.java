package by.zhigalko.snow.world.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {
    private static volatile SessionFactory instance = null;
    private static volatile Session instanceSession = null;

    public static SessionFactory getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return instance;
    }
    public static Session getSession() {
        if (instanceSession == null) {
            return getInstance().openSession();
        } else {
            return instanceSession;
        }
    }

    public static void closeSessionFactory() {
        getInstance().close();
    }

    public static void closeSession() {
        getSession().close();
    }
}
