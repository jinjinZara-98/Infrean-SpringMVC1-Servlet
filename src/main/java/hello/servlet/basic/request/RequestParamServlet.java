package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 * <p>
 * 2. 동일한 파라미터 전송 가능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */
//get post 둘 다 이걸로 데이터 얻어올 수 있음
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회] - start");
         /*
         Enumeration<String> parameterNames = request.getParameterNames();
         while (parameterNames.hasMoreElements()) {
         String paramName = parameterNames.nextElement();
         System.out.println(paramName + "=" +
        request.getParameter(paramName));
         }
         */

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->
                        System.out.println(paramName + "=" + request.getParameter(paramName)));
        //get방식은 contenttype없음, post방식도 request.getParameter 가능
        //paramName는 이름 request.getParameter은 paramName키에 해당하는 값
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");

        String username = request.getParameter("username");
        System.out.println("request.getParameter(username) = " + username);

        String age = request.getParameter("age");
        System.out.println("request.getParameter(age) = " + age);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(username)");
        //하나의 파라미터에 여러 값들이 들어갈 수 있음
        //여러가지 이름이 있으면 getParameterValues로 꺼내 배열에 대입, 여러 값이 있을때 request.getParameter 쓰면 첫번째 값 나옴
        //username=hello&username=kim
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username=" + name);
        }
        //홈페이지 화면에 출력
        response.getWriter().write("ok");
    }
}
