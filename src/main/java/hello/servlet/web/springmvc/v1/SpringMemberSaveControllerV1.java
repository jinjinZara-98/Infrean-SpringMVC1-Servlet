package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//스프링이 자동으로 스프링 빈으로 등록한다.
//내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨
//@RequestMappingHandlerMapping에서 꺼내는 대상이 됨
@Controller
public class SpringMemberSaveControllerV1 {

    //싱글톤이므로 new로 객체 생성 못함 MemberRepository 객체 가져옴
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출
    //url이 /springmvc/v1/members/save 이 경로로 들어오면
    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        //파라미터 이름이 username인 값 age인 값을 갖고옴
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        //Member객체 파라미터에 대입
        Member member = new Member(username, age);
        System.out.println("member = " + member);
        //저장소에 추가
        memberRepository.save(member);

        //논리적이름, 상대경로만 일단 반환, application.properties에 경로 앞, 뒤에 붙일거 지정해놔서
        //뷰리졸버가 jsp 전체 경로 반환해 jsp 실행되어 바로 페이지 띄워줌
        ModelAndView mv = new ModelAndView("save-result");
        //ModelAndView객체에 Member객체 추가
        mv.addObject("member", member);

        return mv;
    }
}
