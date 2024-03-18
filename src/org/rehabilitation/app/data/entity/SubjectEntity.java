package org.rehabilitation.app.data.entity;

public class SubjectEntity {
    private int idSubject;
    private String name;
    private int classroom;

    public SubjectEntity(int idSubject, String name, int classroom) {
        this.idSubject = idSubject;
        this.name = name;
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "SubjectEntity{" +
                "idSubject=" + idSubject +
                ", name='" + name + '\'' +
                ", classroom=" + classroom +
                '}';
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }
}
