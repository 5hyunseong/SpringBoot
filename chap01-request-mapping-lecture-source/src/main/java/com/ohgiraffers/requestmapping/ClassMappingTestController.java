package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/* 설명. 클래스 레벨에 @RequestMapping 어노테이션을 사용할 수 있다.
 *  클래스 레벨에 공통 URL 부분을 설정하고 나면, 매번 메소드에 URL의 중복되는 부분을 작성하지 않아도 된다.
 *  이 때, 와일드카드(*)를 이용해서 조금 더 포괄적인 URL 패턴을 설정할 수 있다.
* */
@Controller
@RequestMapping("/order/*")
public class ClassMappingTestController {

    /* 목차. 1. Class 레벨 매핑*/
    @GetMapping("/regist")
    public String registOrder(Model model) {

        model.addAttribute("message", "GET 방식의 주문 등록용 핸들러 메소드 호출됨.");

        return "mappingResult";
    }

    /* 목차. 2. 여러 개의 패턴 매핑
     *  @RequestMapping의 value 속성에 중괄호를 이용해 매핑할 URL을 나열한다.
    *  */
//    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.POST)
    @PostMapping({"modify", "delete"})
    public String modifyAndDelete(Model model) {

        model.addAttribute("message", "POST 방식의 주문 정보 수정과 주문 정보 삭제 공통 처리용 핸들러 메소드 호출됨.");

        return "mappingResult";
    }

    @GetMapping("/detail/{orderNo}")
    public String selectOrderDetail (Model model, @PathVariable("orderNo") int orderNo) {

        model.addAttribute("message", orderNo + "번 주문 상세 내용 조회용 핸들러 메소드 호출함.");

        return "mappingResult";
    }
}
