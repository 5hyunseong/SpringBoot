package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/first")   // 필기. "/first"나 "/first/*" 동작은 동일하다.
/* 설명. 해당 컨트롤러의 핸들러 메소드에서 Model에 'id'라는 key 값으로 담는 값들은
 *  Session에 담아야 한다고 알리는 어노테이션.
 * */
@SessionAttributes("id")
public class FirstController {

    /* 설명. 핸들러 메소드에 파라미터로 몇 가지 특정 타입을 선언하게 되면 핸들러 메소드 호출 시 인자로 값을 전달한다. */

    /* 설명. 컨트롤러 핸들러 메서드의 반환값을 void로 설계하면, 즉 view의 이름을 반환하지 않으면
     *  요청 주소가 곧 view의 이름이 된다.
     *  ex) /first/regist 요청이 들어온다면 /first/regist 뷰가 응답할 것이다.
    * */
    @GetMapping("regist")
    public void regist() {}

    /* 목차. 1. WebRequest로 요청 데이터를 파라미터로 전달 받기:
     *  핸들러 메소드의 파라미터 선언부에 WebRequest 타입을 선언하면
     *  해당 메소드 호출 시 요청 페이지의 데이터를 전달 인자로써 전달받을 수 있다.
     *  핸들러 메소드 매개변수(parameter)로 Servlet을 배울 때 다루던 HttpServletRequest, HttpServletResponse도 사용 가능하다.
     *  (상위 타입인 ServletRequest, ServletResponse도 사용 가능하다)
     *  다만, WebRequest는 HttpServletRequest의 요청 정보를 거의 대부분 그대로 가지고 있는 API로, Servlet에 종속적이지 않다.
     *  HttpServletRequest는 Servlet API의 일부이고, WebRequest는 Spring의 일부이기 때문에
     *  Spring 기반의 프로젝트에서 더 자주 사용된다.
     *  --------------------------------------------------------------------------------------------------------------------
     *  요약하자면 Spring에서 요청 페이지로부터 사용자의 입력 데이터를 받아오고자 request를 사용할 일이 있다면
     *  WebRequest를 사용하고, 동적 페이지에 전달할 데이터를 담는 용기로써 Model을 사용하면 된다.
    * */
    @PostMapping("regist")
    public String registMenu(Model model, WebRequest req) {

        String name = req.getParameter("name");
        int price = Integer.valueOf(req.getParameter("price"));
        int categoryCode = Integer.valueOf(req.getParameter("categoryCode"));

        String message = name + "을(를) 신규 메뉴 목록의"
                + categoryCode + "번 카테고리에 "
                + price + "원으로 등록 완료.";

        System.out.println("message = " + message);

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @GetMapping("modify")
    public void modify() {}

    /* 목차. 2. @ReuqestParam로 요청 데이터를 파라미터로 전달 받기.
     *  요청 파라미터를 매핑하여 호출 시 값을 넣어주는 어노테이션으로 매개변수 앞에 작성한다.
     *  form의 name 속성값과 매개변수의 이름이 다른 경우 @RequestParam("name")을 정의하면 된다.
     *  또한 어노테이션은 생략 가능하지만, 명시적으로 작성하는 것이 작성자의 의도나 의미를 파악하기에 쉽다.
     *  -----------------------------------------------------------------------------------------------------
     *  전달하는 form의 name 속성이 일치하는 것이 없는경우, 400 에러가 발생하는데 이는 required 속성의 기본값이 true이기 때문이다.
     *  required 속성을 false로 정의하면 해당 name값이 존재하지 않아도 null로 처리하며 에러가 발생하지 않는다.
     *  값이 넘어오지 않게 되면 빈 문자열("")이 넘어오게 되는데, 이 때 컨트롤러 단(controller-side)에서 parsing 관련 에러가 발생할 수 있다.
     *  값이 넘어오지 않는 경우 defaultValue를 이용하게 되면 기본값으로 사용할 수 있다.
     * */
    @PostMapping("modify")
    public String modifyMenuPrice(Model model,
                                  @RequestParam(required = false) String modifyName,
                                  @RequestParam(defaultValue = "0") int modifyPrice) {

        String message = modifyName + "메뉴의 가격을 "
                + modifyPrice + "원으로 가격을 변경하였습니다.";

        System.out.println("message = " + message);
        
        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    /* 설명. 파라미터가 여러 개인 경우, Map으로 한 번에 처리할 수도 있다.
     *  이 때, Map의 key값은 form의 name 속성과 일치한다.
     * */
    @PostMapping("modifyAll")
    public String modifyMenu(Model model, @RequestParam Map<String, String> parameters) {

        String modifyName2 = parameters.get("modifyName2");
        int modifyPrice2 = Integer.valueOf(parameters.get("modifyPrice2"));

        String message = modifyName2 + "메뉴의 가격을 "
                + modifyPrice2 + "원으로 가격을 변경하였습니다.";

        System.out.println("message = " + message);

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @GetMapping("search")
    public void search() {}

    /* 목차. 3. @ModelAttribute를 이용하는 방법
     *  DTO 같은 모델을 커맨드 객체로 전달받는다.
     *
     * 설명.
     *  @ModelAttribute의 경우 커맨드 객체를 생성해 매개변수로 전달해준 뒤, 해당 인스턴스를 model에 담는다.
     *  화면(searchResult.html)에서 출력해보면 모델에 담겨진 값을 확인할 수 있다.
     *
     * 설명.
     *  경우에 따라서 form에 입력한 값을 다음 화면으로 바로 전달해야 하는 경우가 발생하는데 이 때 간편하게 사용 가능하다.
     *  @ModelAttribuate("모델에담을Key값")의 형태로 정의할 수 있으며 지정하지 않으면 타입의 앞글자를 소문자로 한
     *  네이밍 규칙을 따른다. (즉, menuDTO로 담긴다.)
     *  해당 어노테이션은 생략이 가능하지만 명시적으로 작성하는 것이 역시나 협업 환경에서 좋다.
     * */
    @PostMapping("search")
    public String searchMenu (@ModelAttribute("menu") MenuDTO menu) {

        System.out.println("menu = " + menu);

        return "first/searchResult";
    }

    @GetMapping("login")
    public void login() {}

    @PostMapping("login1")
    public String sessionTest(HttpSession session, @RequestParam String id) {

        session.setAttribute("id", id);

        return "first/loginResult";
    }

    @GetMapping("logout1")
    public String logoutTest1(HttpSession session) {

        session.invalidate();

        return "first/loginResult";
    }

    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id) {

        model.addAttribute("id", id);

        return "first/loginResult";
    }

    @GetMapping("logout2")
    public String logoutTest2 (SessionStatus sessionStatus) {

        sessionStatus.setComplete();

        return "first/loginResult";
    }

    @GetMapping("body")
    public void body() {}

    @PostMapping("body")
    public void bodyTest(@RequestBody String body,
                           @RequestHeader("content-type") String contentType,
                           @CookieValue(value="JSESSIONID", required = false) String sessionId)
            throws UnsupportedEncodingException {

        System.out.println("contentType = " + contentType);
        System.out.println("sessionId = " + sessionId);
        System.out.println("body = " + body);
        System.out.println(URLDecoder.decode(body, "UTF-8"));

    }
}
