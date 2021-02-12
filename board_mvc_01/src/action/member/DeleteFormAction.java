package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String id = request.getParameter("id");
		
		request.setAttribute("id", id);
		
		return "/member/deleteForm.jsp";
	}//requestPro()
}//class
