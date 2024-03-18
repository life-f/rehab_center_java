package org.rehabilitation.app.data.manager;

import org.rehabilitation.app.data.entity.SubjectEntity;
import org.rehabilitation.app.util.BaseManager;
import org.rehabilitation.app.util.MysqlDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectManager extends BaseManager {
    public SubjectManager(MysqlDatabase database) {
        super(database);
    }

    public SubjectEntity getById(int id) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM subject WHERE idSubject=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);

            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return new SubjectEntity(
                        resultSet.getInt("idSubject"),
                        resultSet.getString("name"),
                        resultSet.getInt("class")
                );
            }
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения предмета по id");
            throwables.printStackTrace();
        }
        return null;
    }

    public List<SubjectEntity> getAll() {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM subject";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<SubjectEntity> subjects = new ArrayList<>();
            while (resultSet.next()) {
                subjects.add(new SubjectEntity(
                        resultSet.getInt("idSubject"),
                        resultSet.getString("name"),
                        resultSet.getInt("class")
                ));
            }
            return subjects;
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения предметов");
            throwables.printStackTrace();
        }
        return null;
    }

    public SubjectEntity getByName(String name) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM subject WHERE name=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, name);

            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return new SubjectEntity(
                        resultSet.getInt("idSubject"),
                        resultSet.getString("name"),
                        resultSet.getInt("class")
                );
            }
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения предмета по id");
            throwables.printStackTrace();
        }
        return null;
    }
}
