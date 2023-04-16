package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. GET 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 * 2. POST HTML Form 전송 기능
 * application/x-www-form-urlencoded 형식은 앞서 GET에서 살펴본 쿼리 파라미터 형식과 같다.
 * 따라서 쿼리 파라미터 조회 메서드를 그대로 사용하면 된다.
 *
 * !정리하면 request.getParameter() 는 GET URL 쿼리 파라미터 형식과 POST HTML Form 형식 둘 다 지원한다.
 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(
                        param -> System.out.println("param = " + param + " : value = " + request.getParameter(param))
                );
        System.out.println("[전체 파라미터 조회] - end" + "\n");

        //http://localhost:8080/request-param?username=hello&age=20
        System.out.println("[단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end" + "\n");

        //http://localhost:8080/request-param?username=hello&username=hello2&age=20
        System.out.println("[중복 파라미터 사용 시 조회] - start");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames)
            System.out.println("username = " + name);
        System.out.println("[중복 파라미터 사용 시 조회] - end" + "\n");

        response.getWriter().write("ok");
    }
}
