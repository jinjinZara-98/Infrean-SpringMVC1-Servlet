package hello.servlet.web.frontcontroller.v4;

import java.util.Map;
public interface ControllerV4 {
    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    //이전에는 paramMap만 넘겼는데 모델이 파라미터로 넘어옴, 프론트컨트롤러가 모델까지 만들어 넘겨줌
    //반환은 뷰이름만 반환
    //ModelView인터페이스가 없고 모델 객체는 파라미터로 전달되기 때문에 그냥 사용 결과는 뷰이름만 반환
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
