package controller;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;//properties 파일 읽어 오려고
import java.util.*;//Map, HashMap

import command.CommandAction;//인터페이스 

public class ControllerDispatcher extends HttpServlet {
private Map<String,Object> map=new HashMap<String,Object>();

   //초기화 작업
   public void init(ServletConfig config) throws ServletException{
     
	   String path = config.getServletContext().getRealPath("/");
	   String pros = path+config.getInitParameter("proFile"); //WEB-INF/command.properties
	   Properties pp = new Properties();
	   FileInputStream fn = null;
	   try{
		   fn = new FileInputStream(pros);
		   pp.load(fn);
	   }catch(Exception e){
		   System.out.println("파일 읽기 예외"+e);
	   }finally{
		   try{
			   fn.close();
		   }catch(Exception e){}
	   }//finally
	   
	   Iterator keys = pp.keySet().iterator();
	   
	   while(keys.hasNext()){//자료가 있는동안 반복
		   
		   String kkey = (String)keys.next();// /board/list.do
		   String className = pp.getProperty(kkey);// action.board.ListAction
		   
		   try{
			   Class commandClass = Class.forName(className);
			   Object commandObject = commandClass.newInstance();
			   
			   map.put(kkey, commandObject);
		   }catch(Exception e){
			   System.out.println("property 파일 내용을 클래스 객체 만드는 동안 예외"+e);
		   }//catch
	   }//while
   }//init()
   
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	  reqPro(request,response); 
   }//doGet()
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	  reqPro(request,response); 
   }//doGet()
   
   //사용자 정의 메서드
   private void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	   
	   String view = ""; //jsp 넣을 변수
	   
	   CommandAction commandAction = null;
	   
	   try{
		   String uri = request.getRequestURI();// /프로젝트명/board/list.do
		   if(uri.indexOf(request.getContextPath())==0){
			   uri = uri.substring(request.getContextPath().length());
		   }//if
		   commandAction = (CommandAction)map.get(uri);
		   view = commandAction.requestPro(request, response);
	   }catch(Throwable e){
		   throw new ServletException(e);
	   }//catch
	   
	   //포워딩
	   request.setAttribute("CONTENT", view);
	   
	   RequestDispatcher rd = request.getRequestDispatcher("/template/template.jsp");
	   rd.forward(request, response);
   }//reqPro()
}//class end