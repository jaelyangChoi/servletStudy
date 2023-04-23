package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//프론트 컨트롤러가 구현과 상관없이 일관성을 갖고 호출할 수 있도록 인터페이스를 구현
public interface ControllerV1 {
    //기존 req,res 로직을 그대로 쓰기 위해 Servlet과 모양을 똑같이.
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
