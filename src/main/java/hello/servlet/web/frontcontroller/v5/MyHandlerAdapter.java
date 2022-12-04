package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    //처음에 클라이언트가 http요청을 컨트롤러를 찾아오고 그에 맞는 어댑터를 찾아오는 역할
    //handler는 컨트롤러임, 처리할 수 있는지 유무를 반환해서 타입 boolean
    boolean supports(Object handler);

    //해당하는 핸들러 호출, ModelView타입으로 반환
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;

}
