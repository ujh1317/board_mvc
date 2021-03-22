package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import mysql.board.*;//DAO,DTO(VO)
import faq.*;
public class UpdateFormAction implements CommandAction {

	@Override //��� ���� �޼��� ���� ������ 
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		FaqDAO dao=FaqDAO.getDao();//dao��ü ��� 
		FaqDTO dto=dao.updateGetArticle(num);//num�� �ش��ϴ� ������ ���� ��´� 
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("dto", dto);
		
		
		
		return "/faq/updateForm.jsp";//�丮��
	}

}//class end
