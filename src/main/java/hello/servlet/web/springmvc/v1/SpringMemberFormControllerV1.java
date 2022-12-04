package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//회원 등록 폼
//스프링이 자동으로 스프링 빈으로 등록한다.
//내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨
//@RequestMappingHandlerMapping에서 꺼내는 대상이 됨
@Controller
public class SpringMemberFormControllerV1 {

    //요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출
    //url이 /springmvc/v1/members/new-form 이 경로로 들어오면
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        //논리적이름, 상대경로만 일단 반환, application.properties에 경로 앞, 뒤에 붙일거 지정해놔서
        //뷰리졸버가 jsp 전체 경로 반환해 jsp 실행되어 바로 페이지 띄워줌
        return new ModelAndView("new-form");
    }
}