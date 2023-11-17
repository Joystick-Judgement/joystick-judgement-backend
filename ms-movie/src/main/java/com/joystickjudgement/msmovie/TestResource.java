package com.joystickjudgement.msmovie;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResource {

    @GetMapping("hi")
    public String hello() {return "Hello World Native!";  }
}
