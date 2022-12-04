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

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    //싱글톤이므로 new로 객체 생성 못함. MemberRepository클래스에 있는 인스턴스 그냥 가져오는
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //파라미터네임이 username인 값을 가져오고 age는 파싱해서 가져옴
         String username = request.getParameter("username");
         int age = Integer.parseInt(request.getParameter("age"));
        //가져온 값을 Member객체 파라미터에 넣어줌
         Member member = new Member(username, age);
         System.out.println("member = " + member);
        //세팅한 Member객체 저장소에 저장
         memberRepository.save(member);

         //Model에 데이터를 보관한다. 파라미터 세팅한 Member객체를 담아 정한 경로로 보내줌
         request.setAttribute("member", member);
         //경로 적어줌
         String viewPath = "/WEB-INF/views/save-result.jsp";
         //컨트롤러에서 뷰로 이동할때 사용
         RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
         //다른 서블릿이나 jsp로 이동하는, 서버 내부에서 다시 호출 발생
         //웹브라우저에 응답이 왔다 다시 요청하는 리다이렉트와는 다름, 리다이렉트는 2번 호출
         //서버안에서 자기들끼리 호출됬다가 jsp호출해 응답을 만들어내고 고객한테 보내는
         //메서드 호출하듯이
         dispatcher.forward(request, response);
     }
}
