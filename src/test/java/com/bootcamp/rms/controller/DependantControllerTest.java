package com.bootcamp.rms.controller;

import com.bootcamp.rms.domain.Dependant;
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
public class DependantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Dependant newDependant = new Dependant();

    @Before
    public void createDependant() throws Exception{
        newDependant.setFirstName("Dummy");
        newDependant.setLastName("Data");
        newDependant.setGender("M");
        newDependant.setDependantType("WF");
        newDependant.setDob(parseStrToDate("2001-01-01"));
        newDependant.setActive(true);
        newDependant.setEmployeeId("dummy-empl-01");

        MvcResult result = this.mockMvc.perform(post("/api/dependant").content(jsonToString(newDependant)).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isCreated())
                .andReturn();

        String s = result.getResponse().getContentAsString();
        String generatedId = s.substring(s.lastIndexOf("id")+5, s.lastIndexOf("id")+5+36);
        newDependant.setId(generatedId);
    }

    @Test
    public void editDependant() throws Exception {
        newDependant.setActive(false);
        this.mockMvc.perform(put("/api/dependant/{id}", newDependant.getId()).content(jsonToString(newDependant)).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newDependant))))
                .andReturn();
    }


    @Test
    public void findDependantAll() throws Exception {
        this.mockMvc.perform(get("/api/dependant/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newDependant))))
                .andReturn();
    }

    @Test
    public void findDependant() throws Exception {
        this.mockMvc.perform(get("/api/dependant/{id}", newDependant.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newDependant))))
                .andReturn();
    }

    @Test
    public void findDependantByEmployeeId() throws Exception {
        this.mockMvc.perform(get("/api/dependant/search/employeeId/{employeeId}", newDependant.getEmployeeId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newDependant))))
                .andReturn();
    }

    @Test
    public void removeDependant() throws Exception {
        this.mockMvc.perform(delete("/api/dependant/{id}", newDependant.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString(jsonToString(newDependant)))))
                .andReturn();
    }
}
