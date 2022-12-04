package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerV3 {

    //ModelView를 넘겨주는
    ModelView process(Map<String, String> paramMap);
}
