package org.rehabilitation.app.data.manager;

import org.rehabilitation.app.data.entity.ScheduleEntity;
import org.rehabilitation.app.data.entity.SubjectEntity;
import org.rehabilitation.app.util.BaseManager;
import org.rehabilitation.app.util.MysqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleManager extends BaseManager {
    public ScheduleManager(MysqlDatabase database) {
        super(database);
    }

    public ScheduleEntity getById(int id) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM schedule, empsub WHERE schedule.EmpSub=empsub.idEmpSub AND idSchedule=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);

            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return new ScheduleEntity(
                        resultSet.getInt("idSchedule"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getInt("Employee"),
                        resultSet.getInt("Subject")
                );
            }
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения расписания по id");
            throwables.printStackTrace();
        }
        return null;
    }

    public List<ScheduleEntity> getBySubject(SubjectEntity subject) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT DISTINCT * FROM schedule, empsub WHERE schedule.EmpSub=empsub.idEmpSub AND Subject=?;";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, subject.getIdSubject());

            ResultSet resultSet = s.executeQuery();
            List<ScheduleEntity> schedule = new ArrayList<>();
            while (resultSet.next()) {
                schedule.add(new ScheduleEntity(
                        resultSet.getInt("idSchedule"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getInt("Employee"),
                        resultSet.getInt("Subject")
                ));
            }
            return schedule;
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения расписания по id");
            throwables.printStackTrace();
        }
        return null;
    }

    public List<String> getDatesBySubject(SubjectEntity subject) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT DISTINCT date FROM schedule, empsub WHERE schedule.EmpSub=empsub.idEmpSub AND date>CURRENT_DATE AND Subject=?;";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, subject.getIdSubject());

            ResultSet resultSet = s.executeQuery();
            List<String> schedule = new ArrayList<>();
            while (resultSet.next()) {
                schedule.add(resultSet.getString("date"));
            }
            return schedule;
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения дат по id");
            throwables.printStackTrace();
        }
        return null;
    }

    public List<ScheduleEntity> getByDateSubject(String date, SubjectEntity subject) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM schedule, empsub WHERE schedule.EmpSub=empsub.idEmpSub AND Subject=? AND date=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, subject.getIdSubject());
            s.setString(2, date);

            ResultSet resultSet = s.executeQuery();
            List<ScheduleEntity> schedule = new ArrayList<>();
            while (resultSet.next()) {
                schedule.add(new ScheduleEntity(
                        resultSet.getInt("idSchedule"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getInt("Employee"),
                        resultSet.getInt("Subject")
                ));
            }
            return schedule;
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения расписания по id");
            throwables.printStackTrace();
        }
        return null;
    }
}
