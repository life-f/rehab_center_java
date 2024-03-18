package org.rehabilitation.app.data.entity;

public class ScheduleEntity {
    private int idSchedule;
    private String date;
    private String time;
    private int idEmployee;
    private int idSubject;

    public ScheduleEntity(int idSchedule, String date, String time, int idEmployee, int idSubject) {
        this.idSchedule = idSchedule;
        this.date = date;
        this.time = time;
        this.idEmployee = idEmployee;
        this.idSubject = idSubject;
    }

    public ScheduleEntity(String date, String time, int idEmployee, int idSubject) {
        this(-1, date, time, idEmployee, idSubject);
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "idSchedule=" + idSchedule +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", idEmployee=" + idEmployee +
                ", idSubject=" + idSubject +
                '}';
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }
}
