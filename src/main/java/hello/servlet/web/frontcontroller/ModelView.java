package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

//v3부터 사용
public class ModelView {

    //뷰 논리적인 이름
    private String viewName;

    //모델 직접 생성, 원하는 데이터를 넣어 필요할때 꺼내 jsp로 쓰는
    private Map<String, Object> model = new HashMap<>();

    //생성자
    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    //lombok쓰지 않고 게터 세터
    public String getViewName() {
        return viewName;
    }
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}

