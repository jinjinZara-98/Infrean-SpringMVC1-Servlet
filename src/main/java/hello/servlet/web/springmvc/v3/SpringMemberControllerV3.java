package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
/**
 * v3
 * Model 도입
 * ViewName 직접 반환
 * @RequestParam 사용
 * @RequestMapping -> @GetMapping, @PostMapping
 */
//v2에서 더 발전
//스프링이 자동으로 스프링 빈으로 등록한다.
//내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨
//@RequestMappingHandlerMapping에서 꺼내는 대상이 됨
@Controller
//요청 정보를 매핑한다. 해당 URL이 호출되면 , 중복되는 부분을 클래스 위에
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    //싱글톤이므로 new로 객체 생성 못함 MemberRepository 객체 가져옴
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //하나의 컨트롤러에 다 넣어버림

    // url이 /springmvc/v2/members 들어오고 /new-form이고 Get방식으로
    @GetMapping("/new-form")
    public String newForm() {
        //논리적이름, 상대경로만 일단 반환, application.properties에 경로 앞, 뒤에 붙일거 지정해놔서
        //뷰리졸버가 jsp 전체 경로 반환해 jsp 실행되어 바로 페이지 띄워줌
        //v2처럼 ModelAndView객체를 반환하는게 아닌 문자열 반환
        return "new-form";
    }

    // url이 /springmvc/v2/members 들어오고 /save이고 Post방식, 입력하고 넘어가니까
    @PostMapping("/save")
    public String save(
            //파라미터 이름이 username인 값 age인 값을 갖고옴
            //request, reponse를 사용하지 않고 request.getParameter이 아닌 어노테이션을 이용해
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {
        //Member객체 파라미터에 대입
        Member member = new Member(username, age);
        //저장소에 추가
        memberRepository.save(member);
        //Model객체에 Member객체 추가
        model.addAttribute("member", member);
        //논리적이름, 상대경로만 일단 반환, application.properties에 경로 앞, 뒤에 붙일거 지정해놔서
        //뷰리졸버가 jsp 전체 경로 반환해 jsp 실행되어 바로 페이지 띄워줌
        return "save-result";
    }

    @GetMapping
    public String members(Model model) {
        //모든 객체 가져와 배열에 대입
        List<Member> members = memberRepository.findAll();
        //Model객체에 배열 추가
        model.addAttribute("members", members);
        //논리적이름, 상대경로만 일단 반환, application.properties에 경로 앞, 뒤에 붙일거 지정해놔서
        //뷰리졸버가 jsp 전체 경로 반환해 jsp 실행되어 바로 페이지 띄워줌
        return "members";
    }
}
