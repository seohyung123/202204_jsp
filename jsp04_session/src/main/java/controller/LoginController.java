package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;

// 확장자패턴
@WebServlet("*.log")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// service 객체 생성
	private LoginService loginService = new LoginService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String contextpath = request.getContextPath();
		
		if(uri.contains("login")) {
			//로그인
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			System.out.println(userid);
			System.out.println(passwd);
			// service 호출
			Map<String, Object> rmap = loginService.loginCheck(userid, passwd);
			// msg: 로그인 성공, 회원 미존재, 비밀번호 불일치 
			
			//로그인 성공시에 세션을 생성
			int code = (int) rmap.get("code");
			if (code ==0) {
				// 세션객체 생성
				HttpSession session =  request.getSession(); // session id별로 session 생성
				// 세션에 값 담기
				session.setAttribute("userid", userid);
				session.setAttribute("passwd", passwd);
				// 세션의 유효시간
				session.setMaxInactiveInterval(60*60*3); //3시간
				
				// 쿠키에 userid 저장
				// idsave 값 읽기
				String idsave = request.getParameter("idsave");
				System.out.println("idsave: "+idsave);
				Cookie useridCookie = new Cookie("userid", userid);
				//useridCookie.setMaxAge(10);
				if(idsave == null) {
					useridCookie.setMaxAge(0);
				}
				response.addCookie(useridCookie);
				
				// 메인으로 이동
				response.sendRedirect(contextpath+"/view/main.jsp");
			}else {
				// 로그인 실패시 로그인으로 이동
				String msg = (String)rmap.get("msg");
				response.sendRedirect(contextpath+"/view/login.jsp?msg="+URLEncoder.encode(msg, "utf-8") );
			}
		}else if (uri.contains("logout")) {
			// 로그아웃
			HttpSession session = request.getSession();
			session.invalidate(); // 모든 세션 변수 삭제 
			
			response.sendRedirect(contextpath+"/view/login.jsp?msg="+URLEncoder.encode("로그아웃 완료", "utf-8") );
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
