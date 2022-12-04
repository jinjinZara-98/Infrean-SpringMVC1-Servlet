package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controleer.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controleer.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controleer.MemberSaveControllerV2;

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
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    //어떤 url이 호출되면 ControllerV1을 꺼내 호출해
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    //서블릿이 생성이 될 때 값을 넣어두게 됨
    //이 url이 호출되면 cntrollerV1 상속받은 클래스를 실행 집어 넣음
    //url매핑정보로 컨트롤러 조회하기 위해
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //System.out.println("FrontControllerServletV1.service");//실제로는 logger로 찍는게 좋음

        //홈페이지에 친 요청된 url을 얻어옴
        String requestURI = request.getRequestURI();

        //얻어온 url이 Map에 넣어둔 url과 매칭되는게 있는지 확인 후 그 클래스 객체를 얻어 와 대입, 다형성
        ControllerV2 controller = controllerMap.get(requestURI);

        //매칭되는 url이 없어 객체가 비어있다면 404띄워줌
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //인터페이스의 메서드, ControllerV2를 상속받은 클래스 Myview의 process 메서드 실행
        //주소를 담은 MyView객체 반환
        //컨트롤러는 호출하지 않고 객체만 생성해 반환, 나머지는 프론트컨트롤러가 처리
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
