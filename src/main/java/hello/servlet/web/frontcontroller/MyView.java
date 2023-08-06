package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private final String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request, response); //redirect가 아니라 서버 내부적으로 호출
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request); //뭘 하는 건지 파악하기 쉽게 메소드로 추출하여 메소드명이 설명이 되게 한다!
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request, response); //redirect가 아니라 서버 내부적으로 호출
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        //JSP는 request.getAttribute()로 데이터를 조회하기 때문에 담아준다.
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
