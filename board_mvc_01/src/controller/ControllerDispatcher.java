package controller;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;//properties ���� �о� ������
import java.util.*;//Map, HashMap

import command.CommandAction;//�������̽� 

//Ŭ���̾�Ʈ�� �޴� ���̴� 
//��Ʈ�ѷ��� �������� ����� 
//��Ʈ�ѷ��� java����, servlet�������� �̷������.
public class ControllerDispatcher extends HttpServlet {
private Map<String,Object> map=new HashMap<String,Object>();//����,��ü����
   
   //init() �ʱ�ȭ �۾�
   public void init(ServletConfig config) throws ServletException{
      String path=config.getServletContext().getRealPath("/");
      
      String pros=path+config.getInitParameter("proFile");//WEB-INF/Command.properties
      Properties pp=new Properties();//��ü����
      
      FileInputStream fn=null;
      try{
         fn=new FileInputStream(pros); //������ �о���� �۾�
         pp.load(fn); //properties ���Ͽ� load�۾�
         
      }catch(Exception ex1){
         System.out.println("���� �б� ���� :"+ex1);
      }finally{
         try{
            fn.close();
         }catch(Exception ex2){}
      }//finally end
      
      Iterator keys=pp.keySet().iterator();
      
      while(keys.hasNext()){//�ڷᰡ �ִ� ���� �ݺ� ó�� 
         /*
              /board/list.do=action.board.ListAction
         */
         
         String kkey=(String)keys.next();//   /board/list.do
         String className=pp.getProperty(kkey);//	action.board.ListAction
         
         try{
        	//Test t = new Test(); ��ü����
            Class commandClass=Class.forName(className);//Ŭ������ ����� 
            Object commandObject=commandClass.newInstance();//��ü�� ����� 
            
            map.put(kkey, commandObject);//map.put(key,value)
//                  							                  ��ü
         }catch(Exception ex3){
            System.out.println("property ���� ������ Ŭ���� ��ü�� ����� ���� ���� �߻� :"+ex3);
         }//catch
      }//while end--
   }//init() end--------------------
   
   public void doGet(HttpServletRequest request,HttpServletResponse response)
   throws ServletException,IOException
   {
      reqPro(request,response);//�޼��� ȣ�� 
   }
   
   public void doPost(HttpServletRequest request,HttpServletResponse response)
   throws ServletException,IOException
   {
      reqPro(request,response);//�޼��� ȣ�� 
   }
   
   //����� ���� �޼��� 
   private void reqPro(HttpServletRequest request,HttpServletResponse response)
   throws ServletException,IOException{
      String view="";//jsp���� ���� 
      
      //����Ŭ���� ������ ���� ��ü ó��
      CommandAction commandAction=null;//Action ���� ���� ���� 
      
      try{
         
         String uri=request.getRequestURI();//   /������Ʈ�̸�/board/list.do
         
         //   /������Ʈ�̸�/board/list.do
         //   /������Ʈ�̸� : request.getContextPath()
         if(uri.indexOf(request.getContextPath())==0){
            uri=uri.substring(request.getContextPath().length());
            //   /board/list.do
            /*
            uri = "/������Ʈ�̸�/board/list.do"
            	   /������Ʈ�̸�   request.getContextPath()
            */
            /*
            String s5 = "�� ����� $45.67";
    	    int i2 = s5.indexOf("$45.67");
    	    String s6 = s5.substring(i2); //i2 ��ġ���� ������ ���ڿ� ����
    	    System.out.println(s6); //$45.67
            */
         }//if
         commandAction=(CommandAction)map.get(uri);
         
         //��Ʈ�ѷ����� Action�޼��� ȣ��, Action������ DAO�޼��� ȣ��
         view=commandAction.requestPro(request, response);//�޼��带 ȣ�� �Ͽ� view ��´� 
         //list.jsp
         
      }catch(Throwable ex7){
         throw new ServletException(ex7);//���� ����
      }//catch end
      
      //view �� ������ �۾�
      request.setAttribute("CONTENT", view); //ch19/list.jsp
      //request.setAttribute("homepage", "/ch19/list.jsp"); //ch19/list.jsp
      
      RequestDispatcher rd=request.getRequestDispatcher("/template/template.jsp");
      rd.forward(request, response);//view(list.jsp)�� �������Ѵ� 
   }//reqPro() end
}//class end