package com.peaksoft.dao;

import com.peaksoft.model.User;
import com.peaksoft.unit.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS  users(" +
                "id BIGSERIAL PRIMARY KEY," +
                "name VARCHAR(50)," +
                "last_name VARCHAR(50)," +
                "age SMALLINT);";

        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("Successfully connected");

    }

    @Override
    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS  users;";
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("Table deleted");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(name+" added to the database");
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        User user = new User();
        user.setId(id);
        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("User deleted");
    }

    @Override
    public List<User> getAllUsers() {
        String SQL = "FROM User";
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> list = session.createSQLQuery(SQL).getResultList();
        session.getTransaction().commit();
        session.close();
        return list;


    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM  User");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
