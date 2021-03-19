 package action.faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import faq.*;
import mysql.board.*;//DAO,DTO

//�������̽� ��� �޾� ������ Ŭ����
public class WriteProAction implements CommandAction{

   @Override
   public String requestPro(HttpServletRequest request,
         HttpServletResponse response) throws Throwable {
       
      request.setCharacterEncoding("UTF-8");
      
      FaqDTO dto=new FaqDTO();//��ü����
      //Ŭ���̾�Ʈ�� ������ ������ �޾Ƽ� dto�� ����
      dto.setNum(Integer.parseInt(request.getParameter("num")));
      dto.setName(request.getParameter("name"));
      dto.setSubject(request.getParameter("subject"));
      
      dto.setRef(Integer.parseInt(request.getParameter("ref")));
      dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
      dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
      dto.setQuestion("1");
      dto.setAnswer("1");
      dto.setContent(request.getParameter("content"));
      dto.setIp(request.getRemoteAddr());
      dto.setPass(request.getParameter("pass"));
      
      FaqDAO dao=FaqDAO.getDao();//dao��ü���
      dao.insertArticle(dto);//dao�޼��� ȣ��
      
      return "/faq/writePro.jsp";//�� ����
   }

}//class end