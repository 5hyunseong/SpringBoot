package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    @GetMapping("other-controller-npe")
    public String otherNullPointerExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0));

        return "/";
    }

    @GetMapping("other-controller-userDefined")
    public String otherUserDefinedExceptionTest() throws MemberRegistException {

        boolean check = true;
        if (check) {
            throw new MemberRegistException("당신같은 사람은 회원으로 받을 수 없습니다.");
        }

        return "/";
    }
}
