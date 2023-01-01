package com.peaksoft.dao;

import com.peaksoft.model.User;
import com.peaksoft.unit.Unit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao{
    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String SQL ="CREATE TABLE  users(" +
                "id SERIAl PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "last_name VARCHAR(45) NOT NULL ," +
                "age SMALLINT NOT NULL);";
        try(Connection connection = Unit.connection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE users;";
        try(Connection connection = Unit.connection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String last_Name, byte age) {
        String SQL = "INSERT INTO users(name,last_name,age) VALUES (?,?,?);";
        try (Connection conn = Unit.connection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setString(2,last_Name);
            statement.setByte(3,age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(name +" базага кошулду");
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id = ?";
        try(Connection conn = Unit.connection();
            PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setLong(1,id);
        }catch (SQLException s){
            s.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM users;";
        List<User> users = new ArrayList<>();
        try (Connection conn = Unit.connection();
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getByte("age"));
                user.setLastName(resultSet.getString("last_name"));

                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return users;

    }

    public void cleanUsersTable() {
        String SQL = "DELETE   FROM users;";
        try(Connection connection = Unit.connection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
