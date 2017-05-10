package com.bootcamp.rms.controller;

import com.bootcamp.rms.common.Utils;
import com.bootcamp.rms.domain.Employee;
import com.bootcamp.rms.domain.History;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Base64Utils;

import java.util.ArrayList;

import static com.bootcamp.rms.common.Utils.jsonToString;
import static com.bootcamp.rms.common.Utils.parseStrToDate;
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
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Employee newEmployee = new Employee();

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.user.name}")
    private String username;

    @Value("${security.user.password}")
    private String password;

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

        MvcResult result = mockMvc.perform(post("/api/employee")
            .header("Authorization", "Bearer " + getAccessToken())
            .content(jsonToString(newEmployee)).contentType("application/json;charset=UTF-8"))
            .andExpect(status().isCreated())
            .andReturn();

        String s = result.getResponse().getContentAsString();
        String generatedId = s.substring(s.indexOf("id")+5, s.indexOf("id")+5+36);
        newEmployee.setId(generatedId);
    }

    @Test
    public void findEmployeeAll() throws Exception {
        mockMvc.perform(get("/api/employee/all")
            .header("Authorization", "Bearer " + getAccessToken()))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(jsonToString(newEmployee))))
            .andReturn();
    }

    @Test
    public void findEmployee() throws Exception {
        this.mockMvc.perform(get("/api/employee/{id}", newEmployee.getId())
            .header("Authorization", "Bearer " + getAccessToken()))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(jsonToString(newEmployee))))
            .andReturn();
    }

    @Test
    public void findEmployeeByName() throws Exception {
        this.mockMvc.perform(get("/api/employee/search/name/{name}", newEmployee.getFirstName())
            .header("Authorization", "Bearer " + getAccessToken()))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(jsonToString(newEmployee))))
            .andReturn();
    }

    @Test
    public void findLoginUser() throws Exception {
        this.mockMvc.perform(get("/api/employee//get-login-user")
            .header("Authorization", "Bearer " + getAccessToken()))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void editEmployee() throws Exception {
        newEmployee.setEmail("DummyEdited@Data.com");
        mockMvc.perform(put("/api/employee/{id}", newEmployee.getId())
            .header("Authorization", "Bearer " + getAccessToken())
            .content(jsonToString(newEmployee)).contentType("application/json;charset=UTF-8"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(jsonToString(newEmployee))))
            .andReturn();
    }

    @After
    public void removeEmployee() throws Exception {
        this.mockMvc.perform(delete("/api/employee/{id}", newEmployee.getId())
            .header("Authorization", "Bearer " + getAccessToken()))
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString(jsonToString(newEmployee)))))
            .andReturn();
    }


    private String getAccessToken() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/oauth/token")
            .header("Authorization", "Basic " + new String(Base64Utils.encode((clientId+":"+clientSecret).getBytes())))
            .param("username", username)
            .param("password", password)
            .param("grant_type", "password"))
            .andReturn().getResponse();
        return new ObjectMapper().readValue(response.getContentAsByteArray(), Utils.OAuthToken.class).accessToken;
    }

}
