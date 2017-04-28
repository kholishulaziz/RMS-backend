package com.bootcamp.rms.controller;

import com.bootcamp.rms.domain.Grade;
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

import static com.bootcamp.rms.common.util.jsonToString;
import static com.bootcamp.rms.common.util.parseStrToDate;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kholishul_A on 28/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Grade newGrade = new Grade();

    @Before
    public void createGrade() throws Exception{
        newGrade.setGrade("JP");
        newGrade.setDevelopmentStage("DS01");
        newGrade.setStartDate(parseStrToDate("2001-01-01"));
        newGrade.setEndDate(parseStrToDate("2010-10-10"));
        newGrade.setEmployeeId("dummy-empl-01");

        MvcResult result = this.mockMvc.perform(post("/api/grade").content(jsonToString(newGrade)).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isCreated())
                .andReturn();

        String s = result.getResponse().getContentAsString();
        String generatedId = s.substring(s.lastIndexOf("id")+5, s.lastIndexOf("id")+5+36);
        newGrade.setId(generatedId);
    }

    @Test
    public void editGrade() throws Exception {
        newGrade.setGrade("PG");
        this.mockMvc.perform(put("/api/grade/{id}", newGrade.getId()).content(jsonToString(newGrade)).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newGrade))))
                .andReturn();
    }


    @Test
    public void findGradeAll() throws Exception {
        this.mockMvc.perform(get("/api/grade/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newGrade))))
                .andReturn();
    }

    @Test
    public void findGrade() throws Exception {
        this.mockMvc.perform(get("/api/grade/{id}", newGrade.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newGrade))))
                .andReturn();
    }

    @Test
    public void findGradeByEmployeeId() throws Exception {
        this.mockMvc.perform(get("/api/grade/search/employeeId/{employeeId}", newGrade.getEmployeeId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newGrade))))
                .andReturn();
    }

    @Test
    public void removeGrade() throws Exception {
        this.mockMvc.perform(delete("/api/grade/{id}", newGrade.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString(jsonToString(newGrade)))))
                .andReturn();
    }
}
