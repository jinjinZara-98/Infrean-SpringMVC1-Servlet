package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//스프링 빈 이름으로 핸들러 찾는, RequestMapping이 순위가 더 높지만 없으므로 다음 순위인 이것으로
//핸들러매핑을 순서대로 실행해 BeanNameUrlHandlerMapping을 실행에 성공 핸들러 OldController반환
//스프링 빈 이름이 /springmvc/old-controller, 빈의 이름으로 url 핸들러매핑
//url에 이 값이 들어오면 실행
//핸들러 어댑터는 supports를 순서대로 호출해
//SimpleControllerHandlerAdapter가 Controller인터페이스 상속하면 대상이 됨
@Component("/springmvc/old-controller")
public class OldController implements Controller {

    //이 메서드 실행
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("OldController.handleRequest");
        //논리적이름, 상대경로만 일단 반환, application.properties에 경로 앞, 뒤에 붙일거 지정해놔서
        //바로 페이지 띄워줌
        return new ModelAndView("new-form");
    }
}
