package hello.servlet.web.springmvc;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DispatcherServletStructure extends FrameworkServlet {
    private List<HandlerMapping> handlerMappings;
    private List<HandlerAdapter> handlerAdapters;
    private List<ViewResolver> viewResolvers;

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doDispatch(request, response);
    }

    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest processedRequest = request;
        HandlerExecutionChain mappedHandler = null;
        ModelAndView mv = null;

        // 1. 핸들러 조회
        mappedHandler = getHandler(processedRequest);
        if (mappedHandler == null) {
            noHandlerFound(processedRequest, response);
            return;
        }

        // 2. 핸들러 어댑터 조회 - 핸들러를 처리할 수 있는 어댑터
        HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

        // 3. 핸들러 어댑터 실행 -> 4. 핸ㅃ들러 어댑터를 통해 핸들러 실행 -> 5. ModelAndView 반환
        mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

        // 뷰 렌더링 호출
        processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
    }


    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, HandlerExecutionChain mappedHandler, ModelAndView mv, Exception exception) throws Exception {
        render(mv, request, response);
    }

    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        View view;
        String viewName = mv.getViewName();

        // 6. 뷰 리졸버를 통해서 뷰 찾기, 7. View 반환
        //view = resolveViewName(viewName, mv.getModelInternal(), locale, request);

        // 8. 뷰 렌더링
        //view.render(mv.getModelInternal(), request, response);
    }

    protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        if (this.handlerMappings != null) {
            for (HandlerMapping mapping : this.handlerMappings) {
                HandlerExecutionChain handler = mapping.getHandler(request);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }

    protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
        if (this.handlerAdapters != null) {
            for (HandlerAdapter adapter : this.handlerAdapters) {
                if (adapter.supports(handler)) {
                    return adapter;
                }
            }
        }
        throw new ServletException("No adapter for handler [" + handler +
                "]: The DispatcherServlet configuration needs to include a HandlerAdapter that supports this handler");
    }

    protected View resolveViewName(String viewName, @Nullable Map<String, Object> model,
                                   Locale locale, HttpServletRequest request) throws Exception {

        if (this.viewResolvers != null) {
            for (ViewResolver viewResolver : this.viewResolvers) {
                View view = viewResolver.resolveViewName(viewName, locale);
                if (view != null) {
                    return view;
                }
            }
        }
        return null;
    }

    private void noHandlerFound(HttpServletRequest processedRequest, HttpServletResponse response) {
    }

    private Exception dispatchException;
    private Locale locale;
}
