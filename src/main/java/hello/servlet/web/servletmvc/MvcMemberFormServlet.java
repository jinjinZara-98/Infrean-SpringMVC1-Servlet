package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//컨트롤러 역할, 여기로 요청이 다 들어와야됨, url이 /servlet-mvc/members/ new-form로 들어오면 실행
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //경로 적어줌
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //컨트롤러에서 뷰로 이동할때 사용
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //다른 서블릿이나 jsp로 이동하는, 서버 내부에서 다시 호출 발생
        //웹브라우저에 응답이 왔다 다시 요청하는 리다이렉트와는 다름, 리다이렉트는 2번 호출
        //서버안에서 자기들끼리 호출됬다가 jsp호출해 응답을 만들어내고 고객한테 보내는
        //메서드 호출하듯이
        dispatcher.forward(request, response);
     }
}
