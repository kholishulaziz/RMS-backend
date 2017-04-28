package com.bootcamp.rms.controller;

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
public class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private History newHistory = new History();

    @Before
    public void createHistory() throws Exception{
        newHistory.setStartDate(parseStrToDate("2001-01-01"));
        newHistory.setEndDate(parseStrToDate("2010-10-10"));
        newHistory.setProjectName("Dummy Project");
        newHistory.setProjectRole("DEV");
        newHistory.setJobDescription("Dummy Job Description");
        newHistory.setEmployeeId("dummy-empl-01");

        MvcResult result = this.mockMvc.perform(post("/api/history").content(jsonToString(newHistory)).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isCreated())
                .andReturn();

        String s = result.getResponse().getContentAsString();
        String generatedId = s.substring(s.lastIndexOf("id")+5, s.lastIndexOf("id")+5+36);
        newHistory.setId(generatedId);
    }

    @Test
    public void editHistory() throws Exception {
        newHistory.setProjectRole("TEST");
        this.mockMvc.perform(put("/api/history/{id}", newHistory.getId()).content(jsonToString(newHistory)).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newHistory))))
                .andReturn();
    }


    @Test
    public void findHistoryAll() throws Exception {
        this.mockMvc.perform(get("/api/history/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newHistory))))
                .andReturn();
    }

    @Test
    public void findHistory() throws Exception {
        this.mockMvc.perform(get("/api/history/{id}", newHistory.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newHistory))))
                .andReturn();
    }

    @Test
    public void findHistoryByEmployeeId() throws Exception {
        this.mockMvc.perform(get("/api/history/search/employeeId/{employeeId}", newHistory.getEmployeeId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newHistory))))
                .andReturn();
    }

    @Test
    public void removeHistory() throws Exception {
        this.mockMvc.perform(delete("/api/history/{id}", newHistory.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString(jsonToString(newHistory)))))
                .andReturn();
    }
}
