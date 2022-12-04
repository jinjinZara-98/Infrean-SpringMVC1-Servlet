package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//hello.servlet 하위 클래스들중 서블릿을 다 찾아 자동으로 등록
@ServletComponentScan
@SpringBootApplication
public class ServletApplication {

	//웹어플리케이션 순서 web.servlet, jsp, web.servletMvc, web.frontController.v1~ v5, springMvc
	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

}
