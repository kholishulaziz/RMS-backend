package com.bootcamp.rms.controller;

import com.bootcamp.rms.domain.Lookup;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kholishul_A on 20/04/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LookupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Lookup newLookup = new Lookup();

    @Test
    public void findLookupAll() throws Exception {
        this.mockMvc.perform(get("/api/lookup/all"))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void findLookupByDataType() throws Exception {
        this.mockMvc.perform(get("/api/lookup/search/dataType/{dataType}", "OFFICE"))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void findLookupByDataCode() throws Exception {
        this.mockMvc.perform(get("/api/lookup/search/dataCode/{dataCode}", "SUB"))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void findLookupByDataTypeandDataCode() throws Exception {
        this.mockMvc.perform(get("/api/lookup/search/dataType/{dataType}/dataCode/{dataCode}","OFFICE","SUB"))
            .andExpect(status().isOk())
            .andReturn();
    }
}
