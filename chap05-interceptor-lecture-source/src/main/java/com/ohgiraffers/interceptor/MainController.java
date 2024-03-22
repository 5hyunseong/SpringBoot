package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class MainController {

//    @GetMapping(value = {"/", "main"})
//    public String main() {
//        return "main";
//    }

    @RequestMapping("/")
    public String defaultLocation() {
        return "main";
    }

    @RequestMapping("/main")
    public void main() {}
}
