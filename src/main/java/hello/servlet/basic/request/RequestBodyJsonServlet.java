package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//url에 /request-bodyjson이 들어오면
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    //JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면
    //Jackson, Gson 같은 JSON 변환라이브러리를 추가해서 사용해야 한다.
    //스프링 부트로 Spring MVC를 선택하면 기본으로 Jackson라이브러리( ObjectMapper )를 함께 제공
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {

        //request.getInputStream()는 메시지 바디의 내용을 바이트코드로 얻을 수 있음
        ServletInputStream inputStream = request.getInputStream();
        //StreamUtils로 바이트코드를 스트링으로 다시 바꿈, StandardCharsets.UTF_8 변환할때 인코딩 정보 알려주기
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);

        //objectMapper.readValue값을 읽음, 값과 변환클래스를 파라미터로 넣어줌
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());

        response.getWriter().write("ok");
    }
}
