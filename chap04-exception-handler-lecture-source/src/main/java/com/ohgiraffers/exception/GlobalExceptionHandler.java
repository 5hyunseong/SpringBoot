package com.ohgiraffers.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException npe) {

        System.out.println("Global 레벨의 Exception 처리.");

        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userDefinedExceptionHandler(Model model, MemberRegistException mre) {

        System.out.println("Global 레벨의 exception 처리");
        model.addAttribute("exception", mre);

        return "error/memberRegist";
    }
}
