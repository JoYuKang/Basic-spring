package basic.springmvc.basic.request;

import basic.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
@Slf4j
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username")String memberName,
            @RequestParam("age")int memberAge
    ){

        log.info("username = {}, age = {}", memberName, memberAge);
        return "OK";
    }
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ){

        log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,
                                 int age
    ) {

        log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true)String username,
                                 @RequestParam(required = false) int age
    ) {

        log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true)String username,
                                      @RequestParam(required = false, defaultValue = "-1") int age
    ) {

        log.info("username = {}, age = {}", username, age);
        return "OK";
    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap
    ) {

        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "OK";
    }
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData
    ) {
        log.info("helloData = {}", helloData);
        return "OK";
    }
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData
    ) {
        log.info("helloData = {}", helloData);
        return "OK";
    }
}
