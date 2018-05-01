package edu.uw.demoappjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.uw.demoappjava.model.HelloDTO;

/**
 * @author Maxime Deravet
 * Date: 5/1/18
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public HelloDTO hello() {
        return getHello();
    }

    @RequestMapping("/hello/{hello}")
    @ResponseBody
    public HelloDTO helloWord(@PathVariable("hello") String hello) {
        return getHello(hello);
    }

    private HelloDTO getHello() {
        return getHello("container-disco");
    }

    private HelloDTO getHello(String hello) {
        return HelloDTO.builder().phrase(getHelloPhrase(hello)).param(hello).build();
    }

    private String getHelloPhrase(String hello) {
        return "Hello " + hello;
    }


}
