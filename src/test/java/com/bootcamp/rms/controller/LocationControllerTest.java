package com.bootcamp.rms.controller;

import com.bootcamp.rms.domain.Location;
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
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Location newLocation = new Location();

    @Before
    public void createLocation() throws Exception{
        newLocation.setStartDate(parseStrToDate("2001-01-01"));
        newLocation.setEndDate(parseStrToDate("2010-10-10"));
        newLocation.setCity("JOG");
        newLocation.setAddress("Dummy address");
        newLocation.setEmployeeId("dummy-empl-01");

        MvcResult result = this.mockMvc.perform(post("/api/location").content(jsonToString(newLocation)).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isCreated())
                .andReturn();

        String s = result.getResponse().getContentAsString();
        String generatedId = s.substring(s.lastIndexOf("id")+5, s.lastIndexOf("id")+5+36);
        newLocation.setId(generatedId);
    }

    @Test
    public void editLocation() throws Exception {
        newLocation.setCity("SUB");
        this.mockMvc.perform(put("/api/location/{id}", newLocation.getId()).content(jsonToString(newLocation)).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newLocation))))
                .andReturn();
    }


    @Test
    public void findLocationAll() throws Exception {
        this.mockMvc.perform(get("/api/location/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newLocation))))
                .andReturn();
    }

    @Test
    public void findLocation() throws Exception {
        this.mockMvc.perform(get("/api/location/{id}", newLocation.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newLocation))))
                .andReturn();
    }

    @Test
    public void findLocationByEmployeeId() throws Exception {
        this.mockMvc.perform(get("/api/location/search/employeeId/{employeeId}", newLocation.getEmployeeId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonToString(newLocation))))
                .andReturn();
    }

    @Test
    public void removeLocation() throws Exception {
        this.mockMvc.perform(delete("/api/location/{id}", newLocation.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString(jsonToString(newLocation)))))
                .andReturn();
    }
}
