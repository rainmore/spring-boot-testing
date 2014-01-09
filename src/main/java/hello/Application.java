package hello;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.*;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
//@EnableWebMvc
@ComponentScan
@EnableAutoConfiguration
public class Application {

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello World!45435";
    }

    @RequestMapping("/name")
    @ResponseBody
    String home1(@RequestParam(value = "name", required = true) String name) {
        return String.format("Hello, %s", name);
    }

    @RequestMapping("/model")
    @ResponseBody
    Person model() {
        return new Person("name1", "code1");
    }

    @RequestMapping("/list/model")
    @ResponseBody
    List<Person> modelList() {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("name1", "code1"));
        list.add(new Person("name1", "code1"));
        list.add(new Person("name1", "code1"));
        list.add(new Person("name1", "code1"));
        return list;
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    Map<String, String> post(@RequestBody Map<String, String> data, BindingResult result) {
        return data;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
