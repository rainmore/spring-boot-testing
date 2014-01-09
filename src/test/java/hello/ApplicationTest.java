package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
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

        this.mockMvc.perform(get("/hello"))
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

    @Test
    public void testPostModel() throws Exception {
        Person p = new Person("na", "co");
        byte[] data = TestUtil.convertObjectToJsonBytes(p);
        System.out.println(data.toString());
        this.mockMvc.perform(post("/post")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(data)
//                .sessionAttr("person", p)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.code").exists())
        ;
    }
}

