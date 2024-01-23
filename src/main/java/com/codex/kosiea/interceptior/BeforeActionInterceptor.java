package com.codex.kosiea.interceptior;

import com.codex.kosiea.dao.UserDAO;
import com.codex.kosiea.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Component("beforeActionInterceptor")
public class BeforeActionInterceptor implements HandlerInterceptor {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//		 System.out.println("principal : " + principal);

		if (principal.toString() != "anonymousUser") {
			UserDetails userDetails = (UserDetails) principal;

			ArrayList<UserDTO> user = userDAO.findByUserID(userDetails.getUsername().toString());
			System.out.println("[ BeforeActionInterceptor ]");

			for (GrantedAuthority user_auth : userDetails.getAuthorities()) {
				System.out.println("user_auth.toString() = " + user_auth.toString());
				if (user_auth.toString().equals("사용자")) {
					//System.out.println("user_auth : " + user_auth);
					request.setAttribute("admin", true);
				}
			}

			request.setAttribute("isLogined", true);
			request.setAttribute("username", user.get(0).getNAME());
//			request.setAttribute("agency", user.get(0).getAGENCY());
//			request.setAttribute("rolename", user.get(0).getROLE_NAME());
//			request.setAttribute("rolecode", user.get(0).getROLE_CODE());
		} else {
			request.setAttribute("isLogined", false);
			request.setAttribute("admin", false);
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
