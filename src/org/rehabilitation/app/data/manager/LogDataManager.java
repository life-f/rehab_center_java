package org.rehabilitation.app.data.manager;

import org.rehabilitation.app.data.entity.LogDataEntity;
import org.rehabilitation.app.util.BaseManager;
import org.rehabilitation.app.util.MysqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogDataManager extends BaseManager {

    public LogDataManager(MysqlDatabase database) {
        super(database);
    }

    public LogDataEntity getById(int id) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM logdata WHERE idLogData=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);

            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return new LogDataEntity(
                        resultSet.getInt("idLogData"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getInt("Client")
                );
            }
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения пользователя по id");
            throwables.printStackTrace();
        }
        return null;
    }

    public LogDataEntity getByLogin(String login) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM logdata WHERE login=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, login);

            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return new LogDataEntity(
                        resultSet.getInt("idLogData"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getInt("Client")
                );
            }
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения пользователя по login");
            throwables.printStackTrace();
        }
        return null;
    }

    public LogDataEntity getByLoginPassword(String login, String password) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM logdata WHERE login=? AND password=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, login);
            s.setString(2, password);

            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return new LogDataEntity(
                        resultSet.getInt("idLogData"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getInt("Client")
                );
            }
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения пользователя по login и password");
            throwables.printStackTrace();
        }
        return null;
    }
}
