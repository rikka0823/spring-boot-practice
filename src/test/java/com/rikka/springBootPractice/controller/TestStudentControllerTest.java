package com.rikka.springBootPractice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestStudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("student/3");
                .get("/student/{studentId}", 3)
                .header("headName", "headValue")
                .param("graduate", "true");


        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(404))
//                .andExpect(jsonPath("$.id", equalTo(3)))
//                .andExpect(jsonPath("$.name", notNullValue()))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        System.out.println(body);
    }

    public void create() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"a\",\n" +
                        "    \"score\": 22.1,\n" +
                        "    \"graduate\": true\n" +
                        "}\n");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }
}