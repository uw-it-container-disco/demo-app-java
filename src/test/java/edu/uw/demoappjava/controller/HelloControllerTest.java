package edu.uw.demoappjava.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Maxime Deravet
 * Date: 5/1/18
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getHelloTest() throws Exception {

        this.mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.param").isNotEmpty())
                .andExpect(jsonPath("$.phrase").isNotEmpty());
    }

    @Test
    public void getHelloParamTest() throws Exception {

        this.mockMvc.perform(get("/hello/toto"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.param").isNotEmpty())
                .andExpect(jsonPath("$.phrase").isNotEmpty());
    }
}
