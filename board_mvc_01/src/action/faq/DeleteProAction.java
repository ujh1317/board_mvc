package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.*;
import command.*;
import mysql.board.*;//DAO,DTO(VO)

//�������̽��� ��� �޾Ƽ� ���� Ŭ���� �ۼ�
public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		request.setCharacterEncoding("UTF-8");

		//�� â���� ������ ���� �ް�
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		String pw=request.getParameter("pass");
		
		//dao �޼��� ȣ���Ͽ� ����� �ް�
		FaqDAO dao=FaqDAO.getDao();//dao��ü ��� 
		int check=dao.deleteArticle(num, pw);//dao�޼��� ȣ���Ͽ� ����� �޴´�
		
		//�ش��(jsp)���� ����� �Ӽ��� ����
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
	
		return "/faq/deletePro.jsp";//�丮��
	}//requestPro() end

}//class end
