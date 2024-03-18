package org.rehabilitation.app.data.manager;

import org.rehabilitation.app.data.entity.ClientEntity;
import org.rehabilitation.app.util.BaseManager;
import org.rehabilitation.app.util.MysqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientManager extends BaseManager {
    public ClientManager(MysqlDatabase database) {
        super(database);
    }

    public ClientEntity getById(int id) {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM client WHERE idClient=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);

            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return new ClientEntity(
                        resultSet.getInt("idClient"),
                        resultSet.getString("firstName"),
                        resultSet.getString("middleName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("case"),
                        resultSet.getInt("interestPayment"),
                        resultSet.getInt("timeLimit"),
                        resultSet.getInt("Employee")
                );
            }
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения клиента по id");
            throwables.printStackTrace();
        }
        return null;
    }
}
