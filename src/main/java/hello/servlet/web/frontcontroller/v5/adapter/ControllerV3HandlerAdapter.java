package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        //파라미러토 들어온 핸들러가 ControllerV3인터페이스를 구현한 것인지
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //Object타입인 핸들러 ControllerV3로 타입변환해 객체 생성
        //supports메서드에서 ControllerV3를 구현한 것인지만 확인, 걸러진 애를 찾아서 handle 호출하기 때문에 변환 가능
        ControllerV3 controller = (ControllerV3) handler;
        //요청에 들어온 파라미터 이름,값을 Map객체에 넣기
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

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
