package org.rehabilitation.app.data.entity;

public class LogDataEntity {
    private int idLogData;
    private String password;
    private String login;
    private int idClient;

    public LogDataEntity(int idLogData, String password, String login, int idClient) {
        this.idLogData = idLogData;
        this.password = password;
        this.login = login;
        this.idClient = idClient;
    }

    public LogDataEntity(String password, String login, int idClient) {
        this(-1, password, login, idClient);
    }

    @Override
    public String toString() {
        return "LogData{" +
                "idLogData=" + idLogData +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", idClient=" + idClient +
                '}';
    }

    public int getIdLogData() {
        return idLogData;
    }

    public void setIdLogData(int idLogData) {
        this.idLogData = idLogData;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
