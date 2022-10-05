package basic.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MappingController {


    @GetMapping("/hello-basic")
    public String helloBasic(){
        log.info("hello-basic");
        return "OK";
    }
    @GetMapping("/mapping-get-v2")
    public String helloBasic2(){
        log.info("hello-basic");
        return "OK";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId")String data){
        log.info("mapping path = {}", data);
        return "OK";
    }

    @GetMapping("mapping/users/{userId}/orders/{order}")
    public String mappingPath2(@PathVariable("userId")String data, @PathVariable("order")String order){
        log.info("mapping path userid = {}, order = {}", data, order);

        return "OK";
    }
    // 파라미터 정보까지 추가
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }



}
