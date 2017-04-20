package com.aziz.rms.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Kholishul_A on 07/04/2017.
 */

@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name="EMPLOYEE_ID")
    private long Id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="GENDER")
    private String gender;

    @Column(name="DOB")
    private Date dob;

    @Column(name="NATIONALITY")
    private String nationality;

    @Column(name="MARITAL_STATUS")
    private String maritalStatus;

    @Column(name="PHONE")
    private String phone;

    @Column(name="SUB_DIVISION")
    private String subDivision;

    @Column(name="STATUS")
    private String status;

    @Column(name="HIRE_DATE")
    private Date hireDate;

    @Column(name="GRADE")
    private String grade;

    @Column(name="DIVISION")
    private String division;

    @Column(name="EMAIL")
    private String email;

    @Column(name="OFFICE")
    private String office;

    @Column(name="ACTIVE")
    private boolean active;

    @OneToMany
    @JoinColumn(insertable = false, updatable = false, name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    private List<History> historyList;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubDivision() {
        return subDivision;
    }

    public void setSubDivision(String subDivision) {
        this.subDivision = subDivision;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }
}
