package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Dust;
import service.DustJSONService;

@WebServlet("*.dust")
public class DustController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DustJSONService dustJSONService = new DustJSONService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String path = request.getContextPath();

		if(uri.contains("list")) {
			String year = request.getParameter("year");
			List<Dust> dlist = dustJSONService.selectList(year);
			System.out.println(dlist);
			
			request.setAttribute("dlist", dlist);
			request.getRequestDispatcher("/view/dustlist.jsp").forward(request, response);
			
		}else if(uri.contains("dbsave")) {
			String year = request.getParameter("year");
			System.out.println(year);
			int cnt = dustJSONService.dustParsing(year);
			
			String msg = URLEncoder.encode(cnt+"건 저장", "utf-8");
			response.sendRedirect(path+"/view/dustlist.jsp?msg="+msg+"&year="+year);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
