package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;
import com.board.user.domain.UserVo;
import com.board.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MenuMapper menuMapper;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	//로그인
	// /loginForm
	@RequestMapping("/loginForm")
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/users/login");
		
		return mv;
	}
	
	// /login?userid=userid&passwd=passwd
	@RequestMapping("/login")
	//public ModelAndView login(@Param String userid, @Param String passwd, HttpServletRequest requet) //이렇게까지 받을 필요 없음
	public ModelAndView login(HttpServletRequest request) { //getSession()을 위해 request를 받았음
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		UserVo userVo = userMapper.login(userid, passwd);
		MenuVo menuVo = menuMapper.getMenu("MENU01");
		String loc;
		
		if(userVo != null) { //로그인 성공 시 수행
			HttpSession session = request.getSession();
			session.setAttribute("login", userVo);
			session.setAttribute("menuVo", menuVo);
			session.setMaxInactiveInterval(30 * 60); //30분 동안 유지
			
			loc = "redirect:/";
		} else { //로그인 실패
			loc = "redirect:/loginForm";
		}
		
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName(loc);
		
		return mv;
	}
	
	//로그아웃
	// /logout
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/loginForm";
	}
}
