package com.nigames.jbdd.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class HelloWorldController {

    @RequestMapping("/hello")
    public ModelAndView helloWorld() {
        final String message = "Hello World, Spring 4!";
        return new ModelAndView("hello", "message", message);
    }

}
