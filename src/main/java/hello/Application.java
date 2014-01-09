package hello;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class Application {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!45435";
    }

    @RequestMapping("/model")
    @ResponseBody
    Model model() {
        return new Model("name1", "code1");
    }

    @RequestMapping("/list/model")
    @ResponseBody
    List<Model> modelList() {
        List<Model> list = new ArrayList<Model>();
        list.add(new Model("name1", "code1"));
        list.add(new Model("name1", "code1"));
        list.add(new Model("name1", "code1"));
        list.add(new Model("name1", "code1"));
        return list;
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    String post(HttpServletRequest request) {
        String s = request.getQueryString();
        if (s == null) return "asdf asdf";
        return s;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
