package com.bootcamp.rms.controller;

import com.bootcamp.rms.domain.Employee;
import com.bootcamp.rms.domain.History;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static com.bootcamp.rms.common.util.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kholishul_A on 20/04/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Employee newEmployee = new Employee();

    @Before
    public void createEmployee() throws Exception{
        newEmployee.setFirstName("Dummy");
        newEmployee.setLastName("Data");
        newEmployee.setGender("M");
        newEmployee.setDob(parseStrToDate("2001-01-01"));
        newEmployee.setNationality("IND");
        newEmployee.setMaritalStatus("M");
        newEmployee.setPhone("081234567890");
        newEmployee.setSubDivision("Java Bootcamp");
        newEmployee.setStatus("P");
        newEmployee.setHireDate(parseStrToDate("2001-01-01"));
        newEmployee.setGrade("PG");
        newEmployee.setDivision("JWT");
        newEmployee.setEmail("Dummy@Data.com");
        newEmployee.setOffice("JOG");
        newEmployee.setActive(true);
        newEmployee.setHistoryList(new ArrayList<History>());

        MvcResult result = this.mockMvc.perform(post("/api/employee").content(jsonToString(newEmployee)).contentType("application/json;charset=UTF-8"))
            .andExpect(status().isCreated())
            .andReturn();

        String s = result.getResponse().getContentAsString();
        String generatedId = s.substring(s.indexOf("id")+5, s.indexOf("id")+5+36);
        newEmployee.setId(generatedId);
    }

    @Test
    public void editEmployee() throws Exception {
        newEmployee.setEmail("DummyEdited@Data.com");
        this.mockMvc.perform(put("/api/employee/{id}", newEmployee.getId()).content(jsonToString(newEmployee)).contentType("application/json;charset=UTF-8"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(jsonToString(newEmployee))))
            .andReturn();
    }

    @Test
    public void findEmployeeAll() throws Exception {
        this.mockMvc.perform(get("/api/employee/all"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(jsonToString(newEmployee))))
            .andReturn();
    }

    @Test
    public void findEmployee() throws Exception {
        this.mockMvc.perform(get("/api/employee/{id}", newEmployee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(jsonToString(newEmployee))))
            .andReturn();
    }

    @Test
    public void findEmployeeByName() throws Exception {
        this.mockMvc.perform(get("/api/employee/search/name/{name}", newEmployee.getFirstName()))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(jsonToString(newEmployee))))
            .andReturn();
    }

    @Test
    public void removeEmployee() throws Exception {
        this.mockMvc.perform(delete("/api/employee/{id}", newEmployee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString(jsonToString(newEmployee)))))
            .andReturn();
    }



}
