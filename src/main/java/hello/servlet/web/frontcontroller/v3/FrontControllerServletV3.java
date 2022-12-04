package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controleer.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controleer.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controleer.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

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
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    //어떤 url이 호출되면 ControllerV1을 꺼내 호출해
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    //서블릿이 생성이 될 때 값을 넣어두게 됨
    //이 url이 호출되면 cntrollerV1 상속받은 클래스를 실행 집어 넣음
    //url매핑정보로 컨트롤러 조회하기 위해
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //System.out.println("FrontControllerServletV1.service");//실제로는 logger로 찍는게 좋음

        //홈페이지에 친 요청된 url을 얻어옴
        String requestURI = request.getRequestURI();

        //얻어온 url이 Map에 넣어둔 url과 매칭되는게 있는지 확인 후 그 클래스 객체를 얻어 와 대입, 다형성
        ControllerV3 controller = controllerMap.get(requestURI);

        //매칭되는 url이 없어 객체가 비어있다면 404띄워줌
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //request를 파라미터로 줘 파라미터 이름,값을 Map객체로 받아
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        //논리 이름 얻어 대입
        String viewName = mv.getViewName();
        //viewResolver메서드 호출해 뷰 논리 이름에 앞 뒤 추가해 물리 이름으로 만듬
        MyView view = viewResolver(viewName);
        //뷰에 출력할 데이터를 담은 모델을 같이 넘겨줘야됨
        view.render(mv.getModel(), request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        //Map을 넘겨줘야 해 parameter 다 꺼내야함
        //모든 파라미터 이름 다 가져옴
        request.getParameterNames().asIterator()
                //paramName는 키, 파라미터 이름이 paramName인 값을 쌍으로 Map객체에 넣음
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
