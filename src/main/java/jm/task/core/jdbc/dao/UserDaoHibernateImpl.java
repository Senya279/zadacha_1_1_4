package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(
                    "CREATE TABLE IF NOT EXISTS User (" +
                            "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                            "name VARCHAR(50)," +
                            "lastName VARCHAR(50)," +
                            "age TINYINT)").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {transaction.rollback();}
            System.out.println("Ошибка создания таблицы" + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS User").executeUpdate();
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction != null) { transaction.rollback();}
            System.out.println("Ошибка удаления таблицы" + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            User user = new User(name,lastName,age);
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) { transaction.rollback();}
            System.out.println("Ошибка сохранения пользователей" + e.getMessage());
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try ( Session session = Util.getSessionFactory().openSession()) {
            User user = session.get(User.class,id);
            transaction = session.beginTransaction();
                session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) { transaction.rollback();}
            System.out.println("Ошибка удаления пользователя" + e.getMessage());
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from User ",User.class).getResultList();
            transaction.commit();
        }catch (HibernateException e ) {
            if (transaction != null) { transaction.rollback();}
            System.out.println("Ошибка вывода пользователей" + e.getMessage());
        }
        return list;

    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User ").executeUpdate();
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) { transaction.rollback();}
            System.out.println("Ошибка удаления всех пользователей" + e.getMessage());
        }
    }
}
