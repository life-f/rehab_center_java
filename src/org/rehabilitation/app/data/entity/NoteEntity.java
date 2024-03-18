package org.rehabilitation.app.data.entity;

public class NoteEntity {
    private int idNote;
    private int idClient;
    private int idSchedule;

    public NoteEntity(int idNote, int idClient, int idSchedule) {
        this.idNote = idNote;
        this.idClient = idClient;
        this.idSchedule = idSchedule;
    }

    public NoteEntity(int idClient, int idSchedule){
        this(-1, idClient, idSchedule);
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "idNote=" + idNote +
                ", idClient=" + idClient +
                ", idSchedule=" + idSchedule +
                '}';
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }
}
