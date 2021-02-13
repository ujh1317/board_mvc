package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.board.*;
import command.*;

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(Integer.parseInt(request.getParameter("num")));
		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setContent(request.getParameter("content"));
		boardDTO.setRank(Integer.parseInt(request.getParameter("rank")));
		
		BoardDAO boardDAO = BoardDAO.getBoardDAO();
		int check = boardDAO.getUpdateDb(boardDTO);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", new Integer(check));
		
		return "/board/updatePro.jsp";
	}//requestPro()
}//class
