package com.board.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//기능을 수행하기 위한 부품의 역할
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//1. 세션에서 회원정보를 검색
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("login");
		
		//요청한 주소 정보 확인
		String requestUrl = request.getRequestURL().toString();
		
		// /login 페이지는 체크에서 제외
		//Interface 설정하는 곳에서 해당경로를 제외할 때 if()를 사용할 필요 없음
//		if(requestUrl.contains("/login")) {
//			return true; //로그인 체크를 중단
//		}
		
		//로그인 체크기능을 중지하려면 주석으로 처리
//		if(obj == null) {
//			//로그인하지 않음 /loginForm 으로 이동
//			response.sendRedirect("/loginForm");
//			return false;
//		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}