package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// urlPatterns = ".../*" 어디서 요청이 들어오든 frontController가 가장 앞에서 받음
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    // 맵핑정보: 어느 url로 호출하면 어느 controller로 전달할지 맵핑
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        // controller 객체 생성 시, myView 객체도 생성
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI(); // uri를 받을 수 있음
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); // 논리이름 ex. new-form
        /* 아래처럼 직접 경로를 작성해도 되나, viewResolver() 메소드를 만들어서 중복되는 절댕경로(/WEB-INF/views/)와 파일형식(.jsp)를 논리이름에 붙여서
          절대경로를 리턴해주는게 더 깔끔함
        * MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        * */

        MyView view = viewResolver(viewName);

        // view.render(req, resp);
        view.render(mv.getModel(), req, resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    /* request의 파라미터 정보를 paramMap에 넘겨줌
    * 디테일하게 필요한 정보만 뽑아서 paramMap에 저장만 해도 상관없지만 메소드 만드는게 더 깔끔함
    */
    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        req.getParameter(paramName)));
        return paramMap;
    }
}
