package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    // v1에서는 controller가 직접 dispatcher.forward()를 사용해 JSP를 렌더링해서 넘겨줬지만,
    // v2에서 controller가 MyView라는 뷰 객체를 넘겨줄거임
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
