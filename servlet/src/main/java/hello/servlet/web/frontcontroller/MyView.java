package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); // JSP 렌더링
    }

    // ModelView 객체 생성 시는 viewpath 와 map<str, obj> model이 필요함
    public void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) throws ServletException, IOException {
        // model 안에 들어 있는 값을 다 꺼내서, req의 req에 값을 다 담음
        model.forEach((key, value) -> req.setAttribute(key, value));
    }
}
