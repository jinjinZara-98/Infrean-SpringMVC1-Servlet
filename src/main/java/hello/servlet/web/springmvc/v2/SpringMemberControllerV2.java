package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * 클래스 단위 -> 메서드 단위
 * @RequestMapping 클래스 레벨과 메서드 레벨 조합
 */
//스프링이 자동으로 스프링 빈으로 등록한다.
//내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨
//@RequestMappingHandlerMapping에서 꺼내는 대상이 됨
@Controller
//요청 정보를 매핑한다. 해당 URL이 호출되면 , 중복되는 부분을 클래스 위에
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    //싱글톤이므로 new로 객체 생성 못함 MemberRepository 객체 가져옴
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //하나의 컨트롤러에 다 넣어버림

    // url이 /springmvc/v2/members 들어오고 /new-form이면
    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        //논리적이름, 상대경로만 일단 반환, application.properties에 경로 앞, 뒤에 붙일거 지정해놔서
        //뷰리졸버가 jsp 전체 경로 반환해 jsp 실행되어 바로 페이지 띄워줌
        return new ModelAndView("new-form");
    }

    // url이 /springmvc/v2/members 들어오고 /save이면
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        //파라미터 이름이 username인 값 age인 값을 갖고옴
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        //Member객체 파라미터에 대입
        Member member = new Member(username, age);
        //저장소에 추가
        memberRepository.save(member);
        //논리적이름, 상대경로만 일단 반환, application.properties에 경로 앞, 뒤에 붙일거 지정해놔서
        //뷰리졸버가 jsp 전체 경로 반환해 jsp 실행되어 바로 페이지 띄워줌
        ModelAndView mav = new ModelAndView("save-result");
        //ModelAndView객체에 Member객체 추가
        mav.addObject("member", member);
        return mav;
    }

    // url이 /springmvc/v2/members
    @RequestMapping
    public ModelAndView members() {
        //모든 객체 가져와 배열에 대입
        List<Member> members = memberRepository.findAll();
        //논리적이름, 상대경로만 일단 반환, application.properties에 경로 앞, 뒤에 붙일거 지정해놔서
        //뷰리졸버가 jsp 전체 경로 반환해 jsp 실행되어 바로 페이지 띄워줌
        ModelAndView mav = new ModelAndView("members");
        //ModelAndView객체에 배열 추가
        mav.addObject("members", members);
        return mav;
    }

}