package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class ResolverController {

    @GetMapping("myString")
    public String myStringReturning(Model model) {

        /* 설명. 문자열로 뷰 이름을 반환한다는 것은:
         *  반환 후 ThymeleafViewResolver에게 resource/templates/ 를 prefix로, .html을 suffix로 설정하여
         *  resource/templates/result.html 파일을 응답 뷰로 만들어내라는 의미가 된다.
         * */
        model.addAttribute("forwardMessage", "문자열로 뷰 이름을 반환함.");

        return "result";
    }

    @GetMapping("myStringRedirect")
    public String myStringRedirect() {

        /* 설명. 접두사로 redirect: 을 붙이면 forward가 아닌, redirect 시키게 된다. */
        return "redirect:/";
    }

    /* 설명. 기본적으로 redirect시에 재요청이 발생하므로, 첫 번째 request scope는 소멸된다.
     *  하지만, Spring에서는 RedirectAttributes 타입을 사용해 redirect 시 속성 값을 저장할 수 있는 기능을 제공한다.
     * */
    @GetMapping("myStringRedirectAttribute")
    public String myStringRedirectFlashAttribute(RedirectAttributes rAttr) {

        /* 설명. 리다이렉트시, flash 영역에 담아서 redirect 할 수 있다.
         *  자동으로 Model에 추가되기 때문에 requestScope에서 값을 꺼내면 된다.
         *  세션에 임시로 값을 담고 소멸하는 방식이기 때문에 session에 동일한 key값이 존재하지 않아야 한다.
         * */
        rAttr.addFlashAttribute("flashMessage1", "RedirectAttributes를 사용해 리다이렉트함.");

        return "redirect:/";
    }

    @GetMapping("modelAndView")
    public ModelAndView modelAndViewReturning(ModelAndView mv) {

        /* 설명. Model과 View를 합친 개념이다.
         *  핸들러 어댑터가 핸들러 메소드를 호출하고 반환받은 문자열을 ModelAndView로 만들어 DispatcherServlet에게 반환한다.
         *  이 때, 문자열을 반환해도 되지만 ModelAndView를 미리 만들어서 반환할 수도 있다.
         * */
        mv.addObject("forwardMessage", "ModelAndView를 이용한 모델과 뷰 반환");
        mv.setViewName("result");

        return mv;
    }

    @GetMapping("modelAndViewRedirect")
    public ModelAndView  modelAndViewRedirect(ModelAndView mv) {

        mv.setViewName("redirect:/");

        return mv;
    }

    @GetMapping("modelAndViewRedirectAttribute")
    public ModelAndView modelAndViewRedirectFlashAttribute(ModelAndView mv, RedirectAttributes rAttr) {

        rAttr.addFlashAttribute("flashMessage2", "ModelAndView를 이용한 RedirectAttribute");
        mv.setViewName("redirect:/");

        return mv;
    }

}
