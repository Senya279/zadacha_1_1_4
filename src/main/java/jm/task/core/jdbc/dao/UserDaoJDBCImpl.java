package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS User (" +
                            "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                            "name VARCHAR(50)," +
                            "lastName VARCHAR(50)," +
                            "age TINYINT)");
        }
        catch (SQLException en) {
            System.out.println("Ошибка!" + en.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Statement drop = Util.getConnection().createStatement()){
            drop.execute("DROP TABLE IF EXISTS User");
        }
        catch (SQLException em) {
            System.out.println("Ошибка!" + em.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prep = Util.getConnection()
                                          .prepareStatement("INSERT INTO User(name,lastName,age) VALUES (?,?,?)")){
            prep.setString(1,name);
            prep.setString(2,lastName);
            prep.setByte(3,age);
            prep.executeUpdate();
        }catch (SQLException ev) {
            System.out.println("Ошибка" + ev.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement remove = Util.getConnection().prepareStatement("DELETE FROM User WHERE id = ?")){
            remove.setLong(1,id);
            remove.executeUpdate();
        }catch (SQLException ec) {
            System.out.println("Ошибка" + ec.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try ( Statement all = Util.getConnection().createStatement();
              ResultSet resultSet = all.executeQuery("SELECT * FROM User")){
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        }catch (SQLException ex) {
            System.out.println("Ошибка" + ex.getMessage());
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement clean = Util.getConnection().createStatement()){
            clean.execute("TRUNCATE TABLE User");
        }catch (SQLException ez) {
            System.out.println("Ошибка" + ez.getMessage());
        }

    }
}
