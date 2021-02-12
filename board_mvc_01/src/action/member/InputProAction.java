package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import member.*;
import java.sql.Timestamp;

public class InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		MemberDTO memberDTO = new MemberDTO();
		String id = request.getParameter("id");
		
		memberDTO.setId(id);
		memberDTO.setPw(request.getParameter("pw"));
		memberDTO.setName(request.getParameter("name"));
		memberDTO.setNick(request.getParameter("nick"));
		memberDTO.setJumin1(request.getParameter("jumin1"));
		memberDTO.setJumin2(request.getParameter("jumin2"));
		memberDTO.setEmail(request.getParameter("email"));
		memberDTO.setZipcode(request.getParameter("zipcode"));

		String addr = request.getParameter("addr");
		String addr2 = request.getParameter("addr2");
		addr = addr+","+addr2;
		memberDTO.setAddr(addr);
		memberDTO.setRegdate(new Timestamp(System.currentTimeMillis()));
		
		MemberDAO memberDAO = MemberDAO.getMemInstance();
		memberDAO.insertMember(memberDTO);
		
		request.setAttribute("id", id);
		
		return "/member/inputPro.jsp";
	}

}//class
