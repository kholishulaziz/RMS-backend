package com.aziz.rms.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Kholishul_A on 20/04/2017.
 */
@Entity
@Table(name="HISTORY")
public class History {
    @Id
    @GeneratedValue
    @Column(name="HISTORY_ID")
    private long Id;

    @Column(name="START_DATE")
    private Date startDate;

    @Column(name="END_DATE")
    private Date endDate;

    @Column(name="NAME")
    private String name;

    @Column(name="ROLE")
    private String role;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="EMPLOYEE_ID")
    private Long employeeId;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
