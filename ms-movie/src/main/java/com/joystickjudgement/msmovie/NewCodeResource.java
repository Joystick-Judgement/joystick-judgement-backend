package com.joystickjudgement.msmovie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewCodeResource {

    @GetMapping("/newCode")
    public String addingNewCode() {
        return "Hello World new code!";
    }

}
