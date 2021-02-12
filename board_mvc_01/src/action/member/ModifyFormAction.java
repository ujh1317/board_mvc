package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import command.*;

public class ModifyFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String id = request.getParameter("id");
		MemberDAO memberDAO = MemberDAO.getMemInstance();
		MemberDTO memberDTO = memberDAO.getMember(id);
		
		String addr = memberDTO.getAddr();
		String ad[] = addr.split(",");
		memberDTO.setAddr(ad[0]);
		
		request.setAttribute("memberDTO", memberDTO);
		request.setAttribute("addr2", ad[1]);
		
		return "/member/modifyForm.jsp";
	}//requestPro()
}//class
