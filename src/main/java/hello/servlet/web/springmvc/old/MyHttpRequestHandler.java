package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//핸들러매핑을 순서대로 실행해 BeanNameUrlHandlerMapping을 실행에 성공 핸들러 OldController반환
//스프링 빈 이름이 /springmvc/old-controller, 빈의 이름으로 url 핸들러매핑
//url에 이 값이 들어오면 실행
//핸들러 어댑터는 supports를 순서대로 호출해
//OldController와 달리 SimpleControllerHandlerAdapter보다 순위가 높은 HttpRequestHandlerAdapter
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
