package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"/",  "main"})
//    @RequestMapping({"/",  "main"})
    public String main() {
        return "main";
    }
}
