package cn.navg.laboratory.interceptor;

import cn.navg.laboratory.utils.JWTutils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class CheckTokenInterceptor implements HandlerInterceptor {
    private static final String USER_CLAIMS = "userClaims";

    //之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查请求头是否包含token
        String token = request.getHeader("Authorization");
        log.info("token:{}", token);



        //如果token以"Bearer "开头,则去掉"Bearer "前缀
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        //如果没有token,则返回false,表示拦截
        if (token == null || token.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //如果有token,则解析token,并将token中的信息存储到request中
        try {
            Claims userMap = JWTutils.ParseToken(token);
            request.setAttribute(USER_CLAIMS, userMap);
        } catch (Exception e) {
            //如果解析token失败,则返回false,表示拦截
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //如果解析token成功,则返回true,表示放行
        return true;
    }

    //之后调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //完成调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
