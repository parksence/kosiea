//package com.codex.kosiea.interceptior;
//
//import com.codex.kosiea.dao.UserDAO;
//import com.codex.kosiea.dto.UserDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//
//@Component("beforeActionInterceptor")
//public class BeforeActionInterceptor implements HandlerInterceptor {
//
//	@Autowired
//	private UserDAO userDAO;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		if (principal.toString() != "anonymousUser") {
//			UserDetails userDetails = (UserDetails) principal;
//
//			ArrayList<UserDTO> user = userDAO.findByUserID(userDetails.getUsername());
//			System.out.println("[ BeforeActionInterceptor ]");
//
//			for (GrantedAuthority user_auth : userDetails.getAuthorities()) {
//				if (user_auth.toString().equals("최고관리자")) {
//					request.setAttribute("admin", true);
//				}
//			}
//
//			request.setAttribute("isLogined", true);
//			request.setAttribute("username", user.get(0).getNAME());
//		} else {
//			request.setAttribute("isLogined", false);
//			request.setAttribute("admin", false);
//		}
//		return HandlerInterceptor.super.preHandle(request, response, handler);
//	}
//
//}
