package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.board.*;
import command.*;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO boardDAO = BoardDAO.getBoardDAO();
		BoardDTO boardDTO = boardDAO.getUpdate(num);
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("boardDTO", boardDTO);
		
		return "/board/updateForm.jsp";
	}//requestPro()
}//class
