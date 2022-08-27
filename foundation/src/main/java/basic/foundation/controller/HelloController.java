package basic.foundation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    static class User{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @GetMapping(value = "hello")
    public String hello(Model model){
        model.addAttribute("data","hello! Spring~");
        return "hello";
    }
    @GetMapping(value = "hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false)String name, Model model){
        model.addAttribute("name",name);
        return "hello-mvc";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public User helloUser(@RequestParam(value = "name")String name){
        User user = new User();
        user.setName(name);

        return user;

    }

}
