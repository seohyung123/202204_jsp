package s01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/J20220329_03")
public class J20220329_03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		int first = Integer.parseInt(request.getParameter("first"));
		int second = Integer.parseInt(request.getParameter("second"));
		String sign = request.getParameter("sign");
		System.out.println(first);
		System.out.println(second);
		System.out.println(sign);
		
		//계산
		double result=0;
		switch(sign) {
		case "+":
			result = first + second; break;
		case "-":
			result = first - second; break;
		case "*":
			result = first * second; break;
		case "/":
			result = (double)first / second; break;
		default:
			System.out.println("기호 에러");
		}
		
		//redirect방식(주소가바뀐다)
		//절대경로 : contextpath포함
		response.sendRedirect("/jsp01/view/servlet/20220329_03_redirect.jsp?result=" + result);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
