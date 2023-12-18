package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private final Connection conn = Util.connect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `new_schema`.`new_table` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(500) NULL,\n" +
                "  `lastname` VARCHAR(500) NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try {
            conn.prepareStatement(sql).execute();
//            System.out.println("Таблица создана");
        } catch (SQLException e) {
//            System.out.println("Не получилось создать таблицу");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE `new_schema`.`new_table`;";
        try {
            conn.prepareStatement(sql).execute();
//            System.out.println("Таблица удалена");
        } catch (SQLException e) {
//            System.out.println("Не удалось дропнуть таблицу");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO `new_schema`.`new_table` (`name`, `lastName`, `age`) VALUES ((?), (?), (?));\n";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
//            System.out.println("Добавлен юзер");
        } catch (SQLException e) {
//            System.out.println("Не удалось сохранить юзера");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM `new_schema`.`new_table` WHERE `ID` = ?";
        try (final PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
//            System.out.println("Удален юзер с " + id + " айди");
        } catch (SQLException e) {
//            System.out.println("Не удалось удалить юзера по айди");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT *\n" +
                "FROM `new_schema`.`new_table`;";
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                userList.add(user);
            }
//            System.out.println("Получены из БД следующие юзеры:");
//            for (User user : userList) {
//                System.out.println(user.toString());
//            }

        } catch (SQLException e) {
//            System.out.println("Не удалось достать всех юзеров");
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "Truncate table `new_schema`.`new_table`";
        try {
            conn.prepareStatement(sql).execute();
//            System.out.println("Таблица очищена");
        } catch (SQLException e) {
//            System.out.println("Не удалось очистить таблицу");
            e.printStackTrace();
        }
    }

}
