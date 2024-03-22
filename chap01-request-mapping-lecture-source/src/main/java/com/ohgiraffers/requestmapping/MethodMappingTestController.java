package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* 설명. DispatcherServlet은 웹 요청을 받는 즉시 @Controller가 달린 컨트롤러 클래스에 처리를 위임한다.
 *  그 과정은 컨트롤러 클래스의 핸들러 메서드에 선언된 다양한 @ReuqestMapping 설정 내용에 따른다.
* */
@Controller
public class MethodMappingTestController {

    /* 설명. 핸들러 메소드(어노테이션을 활용해서 '요청 방식(GET or POST)' 및 '경로'에 따라 각각 메소드가 작성됨)  */
    /* 목차. 1. 메소드 방식 미지정
     *  @RequestMapping 어노테이션에 요청 URL만 작성하고 method 방식을 지정하지 않으면 GET, POST 등 모든 요청에 응답하게 된다.
     * */
    @RequestMapping("/menu/regist")
    public String registMenu(Model model) {

        /* 설명. Model 객체는 응답할 페이지의 재료를 담는 객체라고 이해하면 된다.
         *  Model 객체에 addAttribute() 메서드를 사용해 key:value 쌍을 추가하면 이후 view에서 꺼내 사용할 수 있다.
         *  (참고로 이 자세한 내용은 chap03-view-resolver에서 다시 다룰 예정이니 해당 챕터는 가볍게 다룬다)
         * */
        model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출함.");
        System.out.println("/menu/regist 핸들러 메소드 호출됨.");

        /* 설명. 핸들러 메소드에 반환하는 String 값은 templates 폴더에 있는 view(.html 파일)의 이름이다.*/
        return "mappingResult"; // 설명. 이 부분 오타나면 골치아픕니다. 짧은 문자열도 복붙합시다.
    }

    /* 목차. 2. 메소드 방식 지정*/
    /* 설명. @ReuqestMapping 어노테이션에 method 방식을 지정할 수 있다. */
    @RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    public String modifyMenu (Model model) {

        model.addAttribute("message", "GET 방식의 메뉴 수정용 핸들러 메소드 호출됨.");

        return "mappingResult";
    }
    /* 설명. POST 방식의 /menu/modify 버튼을 누르면 에러(405)가 발생한다.
     *  이는 RequestDispatcher가 'method 방식'과 '요청 URL'이 일치하는 핸들러 메소드를 찾지 못했기 때문에 발생한 에러다.
    * */

    /* 목차. 3. 요청 메소드 전용 어노테이션 (since ver 4.3)
     *  요청메소드   |   어노테이션
     *  ------------------------------
     *  POST        |   @PostMapping
     *  GET         |   @GetMapping
     *  PUT         |   @PutMapping
     *  DELETE      |   @DeleteMapping
     *  PATCH       |   @PatchMapping
     *  이 어노테이션들은 @RequestMapping 어노테이션에 method속성을 사용해 요청 방법을 지정하는 것과 동일하게 동작한다.
     *  각 어노테이션은 해당하는 요청 메소드에 대해서만 처리할 수 있도록 제한시키는 역할을 한다.
    * */
    @GetMapping("/menu/delete")
    public String getDeleteMenu(Model model) {

        model.addAttribute("message", "GET 방식의 메뉴 삭제용 핸들러 메소드 호출됨.");

        return "mappingResult";
    }

    @PostMapping("/menu/delete")
    public String postDeleteMenu(Model model) {

        model.addAttribute("message", "POST 방식의 메뉴 삭제용 핸들러 메소드 호출됨.");

        return "mappingResult";
    }
}
