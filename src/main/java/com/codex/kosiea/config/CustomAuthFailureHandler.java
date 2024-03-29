package com.codex.kosiea.config;

import com.codex.kosiea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String username = request.getParameter("username"); // 이게 연락처
		String password = request.getParameter("password");

		// 로그인할 때 아이디, 비밀번호 값 담기
		Map<String, Object> loginInfo = new HashMap();
		loginInfo.put("tel", username);
		loginInfo.put("password", password);

		// 아이디 존재 여부 체크하여 있으면 : 1 없으면 : 0
		int userYn = userService.idFailYnCheck(loginInfo);
		if(userYn > 0) {
			// 암호화 비밀번호 체크
			boolean passwordCheck = userService.failPasswordCheck(loginInfo);
			if (!passwordCheck) {
				// 비밀번호가 틀렸을 경우 에러코드 1 반환
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('비밀번호가 틀렸습니다.'); location.replace('/');</script>");
				out.flush();
				return;
			}
		} else {
			// 신규가입 페이지로 이동
			if (!username.equals("") && !password.equals("")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원명단에 없는 정보입니다. \\n신규등록 화면으로 이동합니다.'); location.replace('create');</script>");
				out.flush();
				return;
			} else {
				// 연락처, 비밀번호 중 값이 없는 경우 에러코드 2 반환
				setDefaultFailureUrl("/login?error=true&exception=2");
			}
		}

		super.onAuthenticationFailure(request, response, exception);
	}
}
