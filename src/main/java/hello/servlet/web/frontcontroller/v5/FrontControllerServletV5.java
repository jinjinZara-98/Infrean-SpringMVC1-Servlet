package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
//import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//frontcontroller/v5로 오는 모든 요청
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //핸들러가 매핑된 Map 데이터
    //어떤 컨트롤러든 다 들어올 수 있게 Object로
    private final Map<String, Object> handlerMappingMap = new HashMap<>();

    //어댑터가 여러 개 담겨있고 그 중에서 하나를 찾아 꺼내 쓰는
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    //생성자
    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        //V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter()); //V4 추가
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //핸들러 갖고오기
        //getHandler로 반환받은 객체 받음
        Object handler = getHandler(request);

        //핸들러 비어있다면 404 보냄
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //핸들러에 맞는 어댑터 가져오기
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        //상대 경로 반환 받음
        //어댑터는 뷰 이름이 아니라 ModelView를 만들어 반환
        ModelView mv = adapter.handle(request, response, handler);

        //반환한 ModelView객체에서 이름을 얻어와 뷰를 얻음
        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), request, response);
    }

    //요청uri를 보고 핸들러 가져오기
    private Object getHandler(HttpServletRequest request) {
        //요청 uri 넘겨줌, uri에 맞는 객체 반환
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    //핸들러에 맞는 어댑터 찾는 메서드
    private MyHandlerAdapter getHandlerAdapter (Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            //파라미터로 들어온 핸들러에 매칭되는 어댑터가 있다면 루프 멈추기 그 어댑터 반환
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        //핸들러에 맞는 어댑터가 없다면
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
