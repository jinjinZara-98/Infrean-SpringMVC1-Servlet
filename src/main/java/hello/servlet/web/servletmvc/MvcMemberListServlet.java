package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//url에 /servlet-mvc/ members이 오면
@WebServlet(name = "mvcMemberListServlet", urlPatterns = "/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MvcMemberListServlet.service");

        //객체를 다 꺼내 배열에 담음
        List<Member> members = memberRepository.findAll();
        //배열을 모델에 담음
        request.setAttribute("members", members);
        String viewPath = "/WEB-INF/views/members.jsp";//경로 지정
        //컨트롤러에서 뷰로 이동할때 사용
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //다른 서블릿이나 jsp로 이동하는, 서버 내부에서 다시 호출 발생
        //웹브라우저에 응답이 왔다 다시 요청하는 리다이렉트와는 다름, 리다이렉트는 2번 호출
        //서버안에서 자기들끼리 호출됬다가 jsp호출해 응답을 만들어내고 고객한테 보내는
        //메서드 호출하듯이
        dispatcher.forward(request, response);
    }
}
