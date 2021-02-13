package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.board.*;
import command.*;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO boardDAO = BoardDAO.getBoardDAO();
		BoardDTO boardDTO = boardDAO.getContent(num);
		
		String content = boardDTO.getContent();
		content = content.replace("\n", "<br>");
		
		request.setAttribute("content", content);
		request.setAttribute("boardDTO", boardDTO);
		request.setAttribute("ref", new Integer(boardDTO.getRef()));
		request.setAttribute("re_step", new Integer(boardDTO.getRe_step()));
		request.setAttribute("re_level", new Integer(boardDTO.getRe_level()));
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", pageNum);
		
		return "/board/content.jsp";
	}//requestPro()
}//class
