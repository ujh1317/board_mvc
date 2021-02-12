package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import command.*;

public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO memberDAO = MemberDAO.getMemInstance();
		int check = memberDAO.userCheck(id, pw);
		
		request.setAttribute("check", check);
		request.setAttribute("id", id);
		request.setAttribute("memId", id);
		
		return "/member/loginPro.jsp";
	}//requestPro()
}//class
