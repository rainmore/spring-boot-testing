package hello;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ApplicationTest {

    MockMvc mockMvc;

    @InjectMocks
    Application controller;


    @Autowired
    private org.springframework.web.context.WebApplicationContext webApplicationContext;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testHome() throws Exception {

        this.mockMvc.perform(get("/"))
           .andExpect(status().isOk())
           .andExpect(content().string("Hello World!45435"))
           ;
    }

    @Test
    public void testHome1() throws Exception {

        String name = "felix";
        this.mockMvc.perform(get("/name").param("name", name))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(String.format("Hello, %s", name)))
        ;

        this.mockMvc.perform(get("/name"))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void testModel() throws Exception {

        this.mockMvc.perform(get("/model"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").exists())
            .andExpect(jsonPath("$.code").exists())
        ;
    }


    @Test
    public void testListModel() throws Exception {

        this.mockMvc.perform(get("/list/model"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$..").isArray())
            .andExpect(jsonPath("$..", hasSize(4)))
        ;
    }
}

