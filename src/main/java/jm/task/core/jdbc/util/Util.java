package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/zadacha_1_1_4";
    private static final String user = "root";
    private static final String password = "1234";
    private static volatile SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {

            synchronized (Util.class) {
                if (sessionFactory == null) {
                    try {
                        Configuration cofiguration = new Configuration();
                        cofiguration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                        cofiguration.setProperty("hibernate.connection.url", url);
                        cofiguration.setProperty("hibernate.connection.username", user);
                        cofiguration.setProperty("hibernate.connection.password", password);
                        cofiguration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                        cofiguration.setProperty("hibernate.show_sql", "true");

                        cofiguration.addAnnotatedClass(User.class);

                        sessionFactory = cofiguration.buildSessionFactory();
                    } catch (HibernateException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Ошибка создания SessionFactore", e);
                    }
                }
            }
        } return sessionFactory;
    }
}
