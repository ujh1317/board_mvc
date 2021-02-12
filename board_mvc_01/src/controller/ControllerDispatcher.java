package controller;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;//properties ���� �о� ������
import java.util.*;//Map, HashMap

import command.CommandAction;//�������̽� 

public class ControllerDispatcher extends HttpServlet {
private Map<String,Object> map=new HashMap<String,Object>();

   //�ʱ�ȭ �۾�
   public void init(ServletConfig config) throws ServletException{
     
	   String path = config.getServletContext().getRealPath("/");
	   String pros = path+config.getInitParameter("proFile"); //WEB-INF/command.properties
	   Properties pp = new Properties();
	   FileInputStream fn = null;
	   try{
		   fn = new FileInputStream(pros);
		   pp.load(fn);
	   }catch(Exception e){
		   System.out.println("���� �б� ����"+e);
	   }finally{
		   try{
			   fn.close();
		   }catch(Exception e){}
	   }//finally
	   
	   Iterator keys = pp.keySet().iterator();
	   
	   while(keys.hasNext()){//�ڷᰡ �ִµ��� �ݺ�
		   
		   String kkey = (String)keys.next();// /board/list.do
		   String className = pp.getProperty(kkey);// action.board.ListAction
		   
		   try{
			   Class commandClass = Class.forName(className);
			   Object commandObject = commandClass.newInstance();
			   
			   map.put(kkey, commandObject);
		   }catch(Exception e){
			   System.out.println("property ���� ������ Ŭ���� ��ü ����� ���� ����"+e);
		   }//catch
	   }//while
   }//init()
   
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	  reqPro(request,response); 
   }//doGet()
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	  reqPro(request,response); 
   }//doGet()
   
   //����� ���� �޼���
   private void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	   
	   String view = ""; //jsp ���� ����
	   
	   CommandAction commandAction = null;
	   
	   try{
		   String uri = request.getRequestURI();// /������Ʈ��/board/list.do
		   if(uri.indexOf(request.getContextPath())==0){
			   uri = uri.substring(request.getContextPath().length());
		   }//if
		   commandAction = (CommandAction)map.get(uri);
		   view = commandAction.requestPro(request, response);
	   }catch(Throwable e){
		   throw new ServletException(e);
	   }//catch
	   
	   //������
	   request.setAttribute("CONTENT", view);
	   
	   RequestDispatcher rd = request.getRequestDispatcher("/template/template.jsp");
	   rd.forward(request, response);
   }//reqPro()
}//class end