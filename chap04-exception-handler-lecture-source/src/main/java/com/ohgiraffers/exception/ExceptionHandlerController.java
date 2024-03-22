package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-npe")
    public String nullPointerExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0));

        return "/";
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException npe) {

        System.out.println("Controller 레벨의 Exception 처리.");

        return "error/nullPointer";
    }

    @GetMapping("controller-userDefined")
    public String userDefinedExceptionTest() throws MemberRegistException {

        boolean check = true;

        if(check) {
            throw new MemberRegistException("당신 같은 사람은 회원으로 받아줄 수 없습니다!");
        }

        return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userDefinedExceptionHandler(Model model, MemberRegistException mre) {

        System.out.println("Controller 레벨의 Exception 처리.");
        model.addAttribute("exception", mre);

        return "error/memberRegist";
    }
}
