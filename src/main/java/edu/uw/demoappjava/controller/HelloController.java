package edu.uw.demoappjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.uw.demoappjava.model.HelloDTO;

/**
 * @author Maxime Deravet Date: 5/1/18
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        addHelloToModel(model);
        return "hello";
    }

    @GetMapping("/hello2")
    public String hello2(Model model) {
        addHelloToModel(model);
        return "hello";
    }

    @GetMapping("/hello/{hello}")
    public String helloWord(@PathVariable("hello") String hello, Model model) {
        addHelloToModel(model, hello);
        return "hello";
    }

    private void addHelloToModel(Model model) {
        addHelloToModel(model, "container-disco");
    }

    private void addHelloToModel(Model model, String hello) {

        HelloDTO helloDTO = HelloDTO.builder()
                .phrase(getHelloPhrase(hello))
                .param(hello)
                .nodeName(System.getenv("K8S_NODE_NAME"))
                .podName(System.getenv("K8S_POD_NAME"))
                .build();

        model.addAttribute("hello", helloDTO);
    }

    private String getHelloPhrase(String hello) {
        return "Hello " + hello + "!";
    }


}
