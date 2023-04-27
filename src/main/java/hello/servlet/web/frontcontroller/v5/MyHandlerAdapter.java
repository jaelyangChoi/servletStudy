package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//어댑터를 사용하는 이유 : 다양한 컨트롤러 인터페이스를 유연하게 사용하기 위해
public interface MyHandlerAdapter {

    //어댑터가 해당 컨트롤러를 처리할 수 있는지 판단하는 메서드
    boolean supports(Object handler); //컨트롤러에 국한된 처리가 아님. 컨트롤러 < 어댑터
    
    //이전에는 프론트 컨트롤러가 실제 컨트롤러를 호출했지만 이제는 이 어댑터를 통해서 실제 컨트롤러가 호출
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException; //프론트컨트롤러가 아닌 어댑터가 처리


}
