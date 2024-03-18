package org.rehabilitation.app.data.entity;

public class ClientEntity {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private int caseNumber;
    private int interestPayment;
    private int timeLimit;
    private int idEmployee;

    public ClientEntity(int id, String firstName, String middleName, String lastName, int caseNumber, int interestPayment, int timeLimit, int idEmployee) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.caseNumber = caseNumber;
        this.interestPayment = interestPayment;
        this.timeLimit = timeLimit;
        this.idEmployee = idEmployee;
    }

    public ClientEntity(String firstName, String middleName, String lastName, int caseNumber, int interestPayment, int timeLimit, int idEmployee) {
        this(-1, firstName, middleName, lastName, caseNumber, interestPayment, timeLimit, idEmployee);
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", caseNumber=" + caseNumber +
                ", interestPayment=" + interestPayment +
                ", timeLimit=" + timeLimit +
                ", idEmployee=" + idEmployee +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public int getInterestPayment() {
        return interestPayment;
    }

    public void setInterestPayment(int interestPayment) {
        this.interestPayment = interestPayment;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
}
