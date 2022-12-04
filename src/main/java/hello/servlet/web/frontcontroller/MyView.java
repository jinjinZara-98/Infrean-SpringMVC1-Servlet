package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//바깥 컨트롤러에서 했던 로직을 여기다 넣음, 화면을 랜더링 하기 위한 역할
public class MyView {
    //뷰 경로
    private String viewPath;

    //생성자
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    //기존의 jsp로 이동하는걸 랜더링, 서블릿에 있는거 그대로 가져옴
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request);
        //모델에 있는 데이터를 Request로 바꾼 후 jsp로 이동
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    //모델에 있는 데이터를 Request로 바꾼다
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        //모델에 있는 데이터 foreach로 다 꺼내서 루프로 돌려 request에 다 담음,
        //jsp는 request.getAttribute로 다 꺼내야 하므로
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}