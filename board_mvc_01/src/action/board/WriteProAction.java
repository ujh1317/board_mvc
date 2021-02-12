package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysql.board.*;
import command.*;

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String writer = (String)session.getAttribute("memId");
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(Integer.parseInt(request.getParameter("num")));
		boardDTO.setWriter(writer);
		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setRef(Integer.parseInt(request.getParameter("ref")));
		boardDTO.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		boardDTO.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		boardDTO.setContent(request.getParameter("content"));
		boardDTO.setIp(request.getRemoteAddr());
		boardDTO.setRank(Integer.parseInt(request.getParameter("rank")));
		
		BoardDAO boardDAO = BoardDAO.getBoardDAO();
		boardDAO.getInsert(boardDTO);
		
		return "/board/writePro.jsp";
	}//requestPro()
}//class
