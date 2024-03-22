package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /* 설명. 아래 패턴의 @GetMapping을 선언하면 다음과 같은 URL 요청을 처리할 수 있다.
     *  - localhost:8080
     *  - localhost:8080/
     *  - localhost:8080/main
     * */
    @GetMapping(value = {"/", "main"})
    public String main() {
        return "main";
    }
}
