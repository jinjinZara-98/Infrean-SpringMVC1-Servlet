package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//helloServlet은 서블릿이름,url에 /hello이 들어오면 이 서블릿 실행하게 설정
//두 개 다 다른 서블릿과 겹치면 안됌
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
//서블릿은 HttpServlet 상속받아야함
public class HelloServlet extends HttpServlet {

    //서블릿이 실행되면 이 서비스도 실행됨
    //http요청이 오면 was(서블릿 컨테이너가 Request, Response 객체를 만들어 서블릿에 줌
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //파라미터 이름이 usernamer인 값을 대입
        //http://localhost:8080/hello?username=heo
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //응답 메시지 보내기, 데이터를 담아서
        //단순문자 text/plain 한글 utf-8
        //이 둘은 헤더정보
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //getWriter()는 httpmessagebody에 데이터 들어감
        //홈페이지에 출려됨, 소스보기해도 같음
        response.getWriter().write("hello " + username);
    }
}
