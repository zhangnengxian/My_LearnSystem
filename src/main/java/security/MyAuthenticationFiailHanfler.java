package security;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationFiailHanfler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Map resultMap = new HashMap();
        resultMap.put("msg",e.getMessage());
        String json = objectMapper.writeValueAsString(resultMap);
         response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
