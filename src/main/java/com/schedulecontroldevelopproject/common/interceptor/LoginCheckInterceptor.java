package com.schedulecontroldevelopproject.common.interceptor;

import com.schedulecontroldevelopproject.user.constant.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {
    // 로그인 안한 사람은 특정 API에 접근 못하게 막는다 ??
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            return false; // 여기서 요청을 막고 Controller까지 도달하지 않음
        }

        return true; // 로그인 되어있으면 다음 단계(Controller)로 진행
    }
}