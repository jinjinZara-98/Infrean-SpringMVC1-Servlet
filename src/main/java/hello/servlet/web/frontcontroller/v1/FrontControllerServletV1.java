package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//일단 프론트 컨트롤러는 HttpServlet 상속받음
///frontcontroller/v1/*는 v1/ 밑에 어떤 url이 들어와도 이 서블릿 호출
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    //어떤 url이 호출되면 ControllerV1을 꺼내 호출해
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    //서블릿이 생성이 될 때 값을 넣어두게 됨
    //이 url이 호출되면 cntrollerV1 상속받은 클래스를 실행 집어 넣음
    //url매핑정보로 컨트롤러 조회하기 위해
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServletV1.service");//실제로는 logger로 찍는게 좋음
        //홈페이지에 친 요청된 url을 얻어옴
        String requestURI = request.getRequestURI();

        //얻어온 url이 Map에 넣어둔 url과 매칭되는게 있는지 확인 후 그 클래스 객체를 얻어 와 대입, 다형성
        ControllerV1 controller = controllerMap.get(requestURI);

        //매칭되는 url이 없어 객체가 비어있다면 404띄워줌
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //인터페이스의 메서드, 오버라이드 되면서 url에 맞는 객체 클래스의 process메서드 호출
        controller.process(request, response);
    }
}
