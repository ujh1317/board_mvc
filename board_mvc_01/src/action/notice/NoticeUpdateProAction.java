package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import notice.*;

public class NoticeUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		NoticeDTO dto = new NoticeDTO();
		
		//dto�� setter�۾�
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(request.getParameter("writer"));
		dto.setTitle(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		
		NoticeDAO dao = NoticeDAO.getNoticeDAO();
		int check = dao.getUpdateDb(dto);
		
		request.setAttribute("check", new Integer(check));
		request.setAttribute("pageNum", pageNum);
		
		return "/notice/noticeUpdatePro.jsp";
	}//requestPro()
}//class
