package org.rehabilitation.app.data.manager;

import org.rehabilitation.app.data.entity.NoteEntity;
import org.rehabilitation.app.util.BaseManager;
import org.rehabilitation.app.util.DialogUtil;
import org.rehabilitation.app.util.MysqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteManager extends BaseManager {

    public NoteManager(MysqlDatabase database) {
        super(database);
    }

    public NoteEntity getById(int id) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM note WHERE idNote=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return new NoteEntity(
                        resultSet.getInt("idNote"),
                        resultSet.getInt("Client"),
                        resultSet.getInt("Schedule")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка получения записи по id");
        }
        return null;
    }

    public List<NoteEntity> getByIdClient(int id) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM note WHERE Client=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet resultSet = s.executeQuery();

            List<NoteEntity> notes = new ArrayList<>();
            while (resultSet.next()) {
                notes.add(new NoteEntity(
                        resultSet.getInt("idNote"),
                        resultSet.getInt("Client"),
                        resultSet.getInt("Schedule")
                ));
            }
            return notes;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int getCountByIdSchedule(int idSchedule) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT COUNT(*) FROM note WHERE Schedule=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, idSchedule);
            ResultSet resultSet = s.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public int getByClientSchedule(int idClient, int idSchedule) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM note WHERE Client=? AND Schedule=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, idClient);
            s.setInt(2, idSchedule);
            ResultSet resultSet = s.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("idNote");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public void add(NoteEntity note) {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO note(Client, Schedule) values(?,?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, note.getIdClient());
            s.setInt(2, note.getIdSchedule());
            s.executeUpdate();

            ResultSet keys = s.getGeneratedKeys();
            if (keys.next()) {
                note.setIdNote(keys.getInt(1));
                return;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DialogUtil.showError("Запись не была добавлена");
        }
    }

    public int deleteById(int id) {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM note WHERE idNote=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);

            return s.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
