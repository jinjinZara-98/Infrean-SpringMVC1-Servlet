package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * http://localhost:8080/response-json
 *
 */
//url에 /response-json로 들어오면
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    //> JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면
    // Jackson, Gson 같은 JSON 변환라이브러리를 추가해서 사용해야 한다.
    // 스프링 부트로 Spring MVC를 선택하면 기본으로 Jackson라이브러리( ObjectMapper )를 함께 제공
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        //http응답으로 json 반환할때는 ContentType /json로 지정
        //json은 스펙상 utf-8로 정의가 되어있음
        response.setHeader("content-type", "application/json");
        response.setCharacterEncoding("utf-8");

        //값 세팅
        HelloData data = new HelloData();
        data.setUsername("kim");
        data.setAge(20);
        //response타입을 json으로 했으니 값을 세팅하면
        //{"username":"kim","age":20} 이렇게 됨

        //writeValueAsString객체를 갖고 값을 써서 문자열로 바꿔라
        //값 세팅한 json객체를 자바 객체로 바꾸는
        String result = objectMapper.writeValueAsString(data);
        response.getWriter().write(result);
    }
}
