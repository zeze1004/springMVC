package hello.springmvc.basic.request;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestParamController {
	// 서블릿이 제공하는 파라미터 조회법
	// /request-param-v1?username=hello&age=20
	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		log.info("username={}, age={}", username, age);
		response.getWriter().write("ok");
	}

	@ResponseBody
	@RequestMapping("/request-param-v2")
	public String requestParamV2(
		@RequestParam("username") String memberName,
		@RequestParam("age") int memberAge) {
		log.info("username={}, age={}", memberName, memberAge);

		return "ok"; // @ResponseBody 없으면 ok란 논리적이름의 view를 찾게 됨
	}

	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParamV3(
		@RequestParam String username, // 파라미터랑 변수명 똑같으면 @RequestParam(변수명) 생략 가능
		@RequestParam int age) {
		log.info("username={}, age={}", username, age);

		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParamV4(String username, int age) { // string, int, integer등 단순 타입이면 @RequestParam 생략가능
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	// 파라미터 기입 필수 여부
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(
		@RequestParam(required = true) String username,
		@RequestParam(required = false) Integer age) { // int는 null이 들어갈 수 없으므로 필수 값 아닐 시 Integer
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	// 파라미터에 'username=' 입력서 ''로 변수가 들어감, 이런 경우를 막기위해 default를 지정
	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamRequired(
		@RequestParam(defaultValue = "guest") String username,
		@RequestParam(required = false, defaultValue = "-1") int age) { // default가 -1이므로 null 걱정 X
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	// 파라미터를 Map으로 조회
	// 파라미터가 2개 이상이면 @RequestParam MultiValueMap(key=[value1, value2, ...]) 사용
	// 일반적으로 파라미터 값은 하나
	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
		log.info("username={}, age={}", paramMap.get("username"),
			paramMap.get("age"));
		return "ok";
	}

}
