package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysql.board.*;
import command.*;

import java.util.*;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null){
			pageNum = "1";
		}//if
		
		int pageSize = 10;
		int pageBlock = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;
		int count = 0;
		int number = 0;
		
		List boardList = null;
		BoardDAO boardDAO = BoardDAO.getBoardDAO();
		
		count = boardDAO.getCount();
		if(count>0){
			boardList = boardDAO.getList(startRow, pageSize);
		}else{
			boardList = Collections.EMPTY_LIST;
		}//else
		
		number = count-(currentPage-1)*pageSize;
		
		int pageCount = count/pageSize+(count%pageSize==0?0:1);
		int startPage = (int)(currentPage/pageBlock)*10+1;
		int endPage = startPage+pageBlock-1;
		
		//jsp���� �����
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("pageBlock", new Integer(pageBlock));
		request.setAttribute("pageCount", new Integer(pageCount));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("boardList", boardList);
		
		return "/board/list.jsp";
	}//requestPro()
}//class
