package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class InterceptorTestController {

    @GetMapping("stopwatch")
    public String handlerMethod() throws InterruptedException {

        System.out.println("핸들러 메소드 호출함.");

        /* 설명. 아무 일도 하지 않는 메소드이기 때문에 수행 시간이 0초에 가까울 것이다.
        *   이를 막기 위해 Thread.sleep()을 호출해서 시간을 생성한다.
        * */
        Thread.sleep(1000);
        System.out.println("1초 경과...");

        Thread.sleep(1000);
        System.out.println("2초 경과...");

        return "result";
    }
}
