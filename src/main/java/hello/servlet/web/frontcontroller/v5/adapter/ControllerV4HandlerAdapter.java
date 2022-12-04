package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        //파라미러토 들어온 핸들러가 ControllerV4인터페이스를 구현한 것인지
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //Object타입인 핸들러 ControllerV4로 타입변환해 객체 생성
        //supports메서드에서 ControllerV4를 구현한 것인지만 확인, 걸러진 애를 찾아서 handle 호출하기 때문에 변환 가능
        ControllerV4 controller = (ControllerV4) handler;
        //요청에 들어온 파라미터 이름,값을 Map객체에 넣기
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        //상대경로 반환
        String viewName = controller.process(paramMap, model);
        //상대경로를 파라미터로 넣은 ModelView객체 생성
        ModelView mv = new ModelView(viewName);
        //모델도 세팅
        mv.setModel(model);

        //이 메서드 반환타입이 ModelView이므로 객체 그대로 반환
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));

        return paramMap;
    }
}
