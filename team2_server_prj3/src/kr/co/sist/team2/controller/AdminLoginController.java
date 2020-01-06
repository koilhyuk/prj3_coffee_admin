package kr.co.sist.team2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.sist.team2.service.AdminLoginService;
import kr.co.sist.team2.vo.AdminLoginVO;;

@SessionAttributes("id")
@Controller
public class AdminLoginController {

	// 1. 관리자 로그인 페이지 매핑
	@RequestMapping(value = "admin_login.do", method = GET)
	public String AdminLoginForm() {

		return "login/server_login";
	}// AdminLoginForm

	// 2. 관리자 로그인 체크
	@RequestMapping(value = "admin_loginChk.do", method = POST)
	@ResponseBody
	public String AdminLoginProcess(String date, AdminLoginVO alVO, HttpServletRequest request) {

		AdminLoginService als = new AdminLoginService();
		JSONObject json = als.adminLoginChk(alVO);
		HttpSession session = request.getSession();
		session.setAttribute("id", alVO.getAdminId());

		return json.toJSONString();

	}// AdminLoginProcess

	@RequestMapping(value = "main_homeOk.do", method = POST)
	public String AdminLoginProcess1() {

		return "redirect:main_home.do";
	}

	@RequestMapping(value = "logout.do")
	public String AdminLogoutProcess(SessionStatus ss, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
			request.getSession().removeAttribute("id");
			ss.setComplete();
			redirectAttributes.addFlashAttribute("id");
		
		return "redirect:admin_login.do";
	}//AdminLogoutProcess
}// class
