package hello.servlet.web.springmvc.old;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component("/springmvc/old-controller") // 스프링 빈으로 등록
										// 빈의 이름으로 핸들러 찾기
public class OldController implements Controller  {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("oldController.handleRequest");
		return new ModelAndView("new-form");
	}
}
