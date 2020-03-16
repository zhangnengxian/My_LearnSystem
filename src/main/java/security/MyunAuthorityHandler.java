package security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyunAuthorityHandler implements AccessDeniedHandler {

   private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        boolean isAjax = isAjaxRequest(request);
        System.out.println("是否是ajax请求：" + isAjax);
        if(!isAjax){
            request.setAttribute("isAjaxRequest", isAjax);
            request.setAttribute("message", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/menuController/unPermissionPage");
            dispatcher.forward(request, response);
        }else{
            String msg="无权操作";
            String json = objectMapper.writeValueAsString(msg);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(json);
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }
}
