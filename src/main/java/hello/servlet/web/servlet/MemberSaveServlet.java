package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//폼에 값 입력 후 저장 버튼 누르면 객체에 값 세팅하고 response객체에도 값을 일일히 다 세팅함
@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    //싱글톤이어서 new로 생성 못함
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("MemberSaveServlet.service");
        //파라미터네임이 username인 값을 가져오고 age는 파싱해서 가져옴
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        //비즈니스 로직, 스프링으로 구현하면 이 코드만 짜면됨
        //가져온 값을 Member객체 파라미터에 넣어줌
        Member member = new Member(username, age);
        System.out.println("member = " + member);
        //세팅한 Member객체 저장소에 저장
        memberRepository.save(member);

        //HTTP메시지 응답으로 html이 나가는
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //ContentType을 html로 했으니 홈페이지 화면에 밑의 내용을 출력하는게 아닌
        //밑내용을 html로 했을 때 결과를 출력
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id=" + member.getId() + "</li>\n" +
                " <li>username=" + member.getUsername() + "</li>\n" +
                " <li>age=" + member.getAge() + "</li>\n" +
                "</ul>\n" +
                //링크
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}