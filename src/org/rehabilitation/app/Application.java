package org.rehabilitation.app;

import org.rehabilitation.app.data.manager.LogDataManager;
import org.rehabilitation.app.ui.AuthForm;
import org.rehabilitation.app.util.BaseForm;
import org.rehabilitation.app.util.DialogUtil;
import org.rehabilitation.app.util.MysqlDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    private static Application instance;
    private final MysqlDatabase database = new MysqlDatabase(
            "localhost",
            "rehabilitation_centre",
            "root",
            "1234"
    );

    private final LogDataManager logDataManager = new LogDataManager(database);

    public Application() {
        instance = this;

        initDatabase();
        initUi();

        new AuthForm();
    }

    public static void main(String[] args) {
        new Application();
    }

    private void initDatabase() {
        try (Connection c = database.getConnection()) {
        } catch (SQLException e) {
            e.printStackTrace();
            DialogUtil.showError("Соединение с базой данных не установлено");
            System.exit(400);
        }
    }

    private void initUi() {
        BaseForm.setBaseApplicationTitle("Реабилитационный центр");
    }

    public static Application getInstance() {
        return instance;
    }

    public MysqlDatabase getDatabase() {
        return database;
    }

    public LogDataManager getLogDataManager() {
        return logDataManager;
    }
}
