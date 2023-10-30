package com.engr.oufoodpantry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloworldcontroller {
    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}
