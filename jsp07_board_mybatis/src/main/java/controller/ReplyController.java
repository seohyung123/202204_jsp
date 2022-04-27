package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Reply;
import service.ReplyService;


//댓글처리 컨트롤러
@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReplyService replyService = new ReplyService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		
		if (uri.contains("add")) {
			//파라메터 dto넣기
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			int restep = Integer.parseInt(request.getParameter("restep"));
			int relevel = Integer.parseInt(request.getParameter("relevel"));
			String content = request.getParameter("content");
			Reply reply = new Reply();
			reply.setBnum(bnum);
			reply.setRestep(restep);
			reply.setRelevel(relevel);
			reply.setContent(content);
			System.out.println(reply);
			replyService.insert(reply);
			
			//게시물의 상세조회 이동
			response.sendRedirect(path+"/board/detail?bnum=" + bnum);
		}else if (uri.contains("remove")) {
			//댓글삭제
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			replyService.delete(rnum);
			
			//detail로 이동시 필요
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			
			//redirect board detail로 이동
			response.sendRedirect(path+"/board/detail?bnum="+bnum );
		}else if (uri.contains("modiform")) {
			//수정폼으로 이동
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			
			//댓글 한건 조회
			Reply reply = replyService.selectOne(rnum);
			//replymodify.jsp로 이동
			request.setAttribute("reply", reply);
			request.getRequestDispatcher("/view/board/replymodify.jsp")
				.forward(request, response);
		}else if (uri.contains("modify")) {	
			//수정
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			String content = request.getParameter("content");
			Reply reply = new Reply();
			reply.setRnum(rnum);
			reply.setContent(content);
			System.out.println(reply);
			replyService.update(reply);
			//redirect board detail로 이동 
			
			
		}else {
			System.out.println("잘못된 uri");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
