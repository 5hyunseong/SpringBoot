package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/* 설명. 핸들러 인터셉터를 구현.
 *  default 메소드 이전에는 모두 오버리이딩 해야하는 책임을 가지기 때문에 HandlerInterceptorAdaptor를 이용해
 *  부담을 줄여 사용했어나, default 메소드가 인터페이스에서 사용 가능하게 된 JDK1.8 부터는 인터페이스만 구현하여
 *  필요한 메소드만 오버라이딩 해서 사용할 수 있다.
 *  ------------------------------------------------------------------------------------------------------------
 *  method overriding 단축키 : ctrl + O
 *  */
@Component
public class StopwatchInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    public StopwatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 설명. 전처리 메소드 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("preHandler 호출됨..");

        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime", startTime);

        /* 설명. 반환형이 Boolean이다:
         *  true : 컨트롤러를 이어서 호출함.
         *  false : 핸들러 메소드를 호출하지 않음.
         * */
        return true;
    }

    /* 설명. 후처리 메소드 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
            throws Exception {

        System.out.println("postHandler 호출됨..");

        long startTime = (long) request.getAttribute("startTime");  // 형변환
//        long startTime = Long.valueOf((String) request.getAttribute("startTime"));

        request.removeAttribute("startTime");

        long endTime = System.currentTimeMillis();

        mv.addObject("interval", endTime - startTime);
    }

    /* 설명. 마지막에 호출되는 메소드 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        System.out.println("afterCompletion 호출됨..");

        menuService.afterMethod();
    }
}
