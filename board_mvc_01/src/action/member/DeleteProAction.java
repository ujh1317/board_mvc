package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import command.*;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MemberDAO memberDAO = MemberDAO.getMemInstance();
		int check = memberDAO.deleteMember(id, pw);
		
		request.setAttribute("check", check);
		
		return "/member/deletePro.jsp";
	}//requestPro()
}//class
