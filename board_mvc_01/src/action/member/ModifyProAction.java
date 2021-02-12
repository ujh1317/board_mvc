package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import command.*;

public class ModifyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setPw(request.getParameter("pw"));
		memberDTO.setNick(request.getParameter("nick"));
		memberDTO.setEmail(request.getParameter("email"));
		memberDTO.setZipcode(request.getParameter("zipcode"));
		
		String addr = request.getParameter("addr");
		String addr2 = request.getParameter("addr2");
		memberDTO.setAddr(addr+","+addr2);
		
		MemberDAO memberDAO = MemberDAO.getMemInstance();
		memberDAO.updateMember(memberDTO);
		
		request.setAttribute("id", id);
		
		return "/member/modifyPro.jsp";
	}//requestPro()
}//class
