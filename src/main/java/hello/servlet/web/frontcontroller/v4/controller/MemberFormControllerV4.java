package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.v4.ControllerV4;
import java.util.Map;


public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        //논리 이름만 반환함
        //ModelView인터페이스가 없고 모델 객체는 파라미터로 전달되기 때문에 그냥 사용 결과는 뷰이름만 반환
        return "new-form";
    }
}
