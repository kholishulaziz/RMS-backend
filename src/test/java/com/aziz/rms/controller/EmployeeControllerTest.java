package com.aziz.rms.controller;

import com.aziz.rms.common.util;
import com.aziz.rms.domain.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;

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
        //newEmployee.setId(3);
        newEmployee.setFirstName("Dummy");
        newEmployee.setLastName("Data");
        newEmployee.setGender("M");
        newEmployee.setDob(util.parseStrToDate("2001-01-01"));
        newEmployee.setNationality("IND");
        newEmployee.setMaritalStatus("M");
        newEmployee.setPhone("081234567890");
        newEmployee.setSubDivision("Java Bootcamp");
        newEmployee.setStatus("P");
        newEmployee.setHireDate(util.parseStrToDate("2001-01-01"));
        newEmployee.setGrade("PG");
        newEmployee.setDivision("JWT");
        newEmployee.setEmail("Dummy@Data.com");
        newEmployee.setOffice("JOG");
        newEmployee.setActive(true);
        newEmployee.setHistoryList(new ArrayList<>());

        this.mockMvc.perform(post("/api/employee").content(asJsonString(newEmployee)).contentType("application/json;charset=UTF-8"))
            .andExpect(content().string(containsString(asJsonString(newEmployee))))
            .andExpect(status().isCreated())
            .andReturn();
    }

    @Test
    public void editEmployee() throws Exception {
        newEmployee.setEmail("DummyEdited@Data.com");
        this.mockMvc.perform(put("/api/employee/{id}", newEmployee.getId()).content(asJsonString(newEmployee)).contentType("application/json;charset=UTF-8"))
            .andExpect(content().string(containsString(asJsonString(newEmployee))))
            .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
            .andReturn();
    }

    @Test
    public void findEmployeeAll() throws Exception {
        this.mockMvc.perform(get("/api/employee"))
            .andExpect(content().string(containsString(asJsonString(newEmployee))))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void findEmployee() throws Exception {
        this.mockMvc.perform(get("/api/employee/{id}", newEmployee.getId()))
            .andExpect(content().string(containsString(asJsonString(newEmployee))))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void findEmployeeByName() throws Exception {
        this.mockMvc.perform(get("/api/employee/search/name/{name}", newEmployee.getFirstName()))
                .andExpect(content().string(containsString(asJsonString(newEmployee))))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void removeEmployee() throws Exception {
        this.mockMvc.perform(delete("/api/employee/{id}", newEmployee.getId()))
            .andExpect(content().string(not(containsString(asJsonString(newEmployee)))))
            .andExpect(status().isOk())
            .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
