package com.hobbymatcher;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    @RequestMapping(name = "/", method= RequestMethod.GET)
    public String hello(){
        return "hello sb";
    }
}
