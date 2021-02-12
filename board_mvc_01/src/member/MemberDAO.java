package member;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class MemberDAO {
	
	private static MemberDAO memInstance = new MemberDAO();//�̱��水ü
	public static MemberDAO getMemInstance(){
		return memInstance;
	}//getInstance()
	
	//Ŀ�ؼ�Ǯ---------------------------------------------------------
	public Connection getConn() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getConn()
	
	//id�ߺ�üũ---------------------------------------------------------
	public int confirmId(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select id from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				x = 1; //������� id
			}else{
				x = -1; //��� ������ id
			}//if
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return x;
	}//confirmId()
	
	//ȸ������---------------------------------------------------------
	public void insertMember(MemberDTO memberDTO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConn();
			String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());
			pstmt.setString(3, memberDTO.getName());
			pstmt.setString(4, memberDTO.getNick());
			pstmt.setString(5, memberDTO.getJumin1());
			pstmt.setString(6, memberDTO.getJumin2());
			pstmt.setString(7, memberDTO.getEmail());
			pstmt.setString(8, memberDTO.getZipcode());
			pstmt.setString(9, memberDTO.getAddr());
			pstmt.setTimestamp(10, memberDTO.getRegdate());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
	}//getMember()
	
	//�α���(����)---------------------------------------------------------
	public int userCheck(String id, String pw) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String dbPw = rs.getString("pw");
				if(pw.equals(dbPw)){
					x = 1; //�α��� ����
				}else{
					x = 0; //��ȣ Ʋ��
				}//else
			}else{
				x = -1; //���� id
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return x;
	}//userCheck()
	
	//ȸ����������---------------------------------------------------------
	public MemberDTO getMember(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO memberDTO = null;
		
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				memberDTO = new MemberDTO();
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPw(rs.getString("pw"));
				memberDTO.setName(rs.getString("name"));
				memberDTO.setNick(rs.getString("nick"));
				memberDTO.setJumin1(rs.getString("jumin1"));
				memberDTO.setJumin2(rs.getString("Jumin2"));
				memberDTO.setEmail(rs.getString("email"));
				memberDTO.setZipcode(rs.getString("zipcode"));
				memberDTO.setAddr(rs.getString("addr"));
				memberDTO.setRegdate(rs.getTimestamp("regdate"));
			}//if
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return memberDTO;
	}//getMember()
	
	//DB����---------------------------------------------------------
	public void updateMember(MemberDTO memberDTO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConn();
			String sql = "update member set pw=?,nick=?,email=?,zipcode=?,addr=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getPw());
			pstmt.setString(2, memberDTO.getNick());
			pstmt.setString(3, memberDTO.getEmail());
			pstmt.setString(4, memberDTO.getZipcode());
			pstmt.setString(5, memberDTO.getAddr());
			pstmt.setString(6, memberDTO.getId());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
	}//updateMember()
	
	//ȸ��Ż��---------------------------------------------------------
	public int deleteMember(String id, String pw) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int x = -1;
		
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String dbPw = rs.getString("pw");
				if(pw.equals(dbPw)){
					pstmt2 = conn.prepareStatement("delete from member where id=?");
					pstmt2.setString(1, id);
					pstmt2.executeUpdate();
					x = 1; //Ż�� ����
				}else{
					x = -1; //Ż�� ����
				}//else
			}//if
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(pstmt2!=null){pstmt2.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return x;
	}//deleteMember()
}//class
