package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// urlPatterns = ".../*" 어디서 요청이 들어오든 frontController가 가장 앞에서 받음
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    // 맵핑정보: 어느 url로 호출하면 어느 controller로 전달할지 맵핑
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1(Map<String, ControllerV1> controllerMap) {
        this.controllerMap = controllerMap;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
