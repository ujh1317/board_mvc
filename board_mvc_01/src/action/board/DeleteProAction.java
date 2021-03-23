package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import mysql.board.*;
public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		String pw=request.getParameter("pw");
		
		BoardDAO dao=BoardDAO.getBoardDAO();
		int check=dao.getDelete(num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		return "/board/deletePro.jsp";
	}//requestPro()

}//class
