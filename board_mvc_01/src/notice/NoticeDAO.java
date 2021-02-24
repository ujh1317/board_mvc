package notice;
import java.util.*;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;

import mysql.board.BoardDTO;


public class NoticeDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	private static NoticeDAO noticeDAO = new NoticeDAO();
	
	public static NoticeDAO getNoticeDAO(){
		return noticeDAO;
	}
	
	private NoticeDAO(){}
	
	private Connection getConn() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getConn()
	
	public void insertNotice(NoticeDTO noticeDTO) throws Exception{
		int num = noticeDTO.getNum();
		int ref = noticeDTO.getRef();
		int re_step = noticeDTO.getRe_step();
		int re_level = noticeDTO.getRe_level();
		int number = 0;
		
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select max(num) from notice");
			rs = pstmt.executeQuery();
			
			if(rs.next()){//글이 있을때
				number = rs.getInt(1)+1;
			}else{
				number = 1;
			}//else
			
			if(num!=0){
				sql = "update notice set re_step = re_step+1 where ref=? and re_step>?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				re_step = re_step+1;
				re_level = re_level+1;
			}else{
				ref = number;
				re_step = 0;
				re_level = 0;
			}//else
			
			sql = "insert into notice(writer,title,regdate,ref,re_step,re_level,content,ip)"
					+ " values(?,?,NOW(),?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticeDTO.getWriter());
			pstmt.setString(2, noticeDTO.getTitle());
			pstmt.setInt(3, ref);
			pstmt.setInt(4, re_step);
			pstmt.setInt(5, re_level);
			pstmt.setString(6, noticeDTO.getContent());
			pstmt.setString(7, noticeDTO.getIp());
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
	}//insertNotice()
	
	public int getCount() throws Exception{
		int x = 0;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select count(*) from notice");
			rs = pstmt.executeQuery();
			if(rs.next()){
				x = rs.getInt(1);
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
	}//getCount()
	
	public List getList(int start, int cnt) throws Exception{
		List<BoardDTO> list = null;
		try{
			conn = getConn();
			sql = "select * from notice order by ref desc, re_step asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1); //시작위치
			pstmt.setInt(2, cnt); //갯수
			rs = pstmt.executeQuery();
		
			if(rs.next()){
				list = new ArrayList<BoardDTO>();
				do{
					BoardDTO boardDTO = new BoardDTO();
					boardDTO.setNum(rs.getInt("num"));
					boardDTO.setWriter(rs.getString("writer"));
					boardDTO.setTitle(rs.getString("title"));
					boardDTO.setContent(rs.getString("content"));
					boardDTO.setRegdate(rs.getString("regdate"));
					boardDTO.setReadcount(rs.getInt("readcount"));
					boardDTO.setRef(rs.getInt("ref"));
					boardDTO.setRe_step(rs.getInt("re_step"));
					boardDTO.setRe_level(rs.getInt("re_level"));
					boardDTO.setIp(rs.getString("ip"));
					list.add(boardDTO);
				}while(rs.next());
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
		return list;
	}//getList()
	
	public BoardDTO getContent(int num) throws Exception{
		BoardDTO boardDTO = null;
		try{
			conn = getConn();
			sql = "update notice set readcount=readcount+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select * from notice where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				boardDTO = new BoardDTO();
				boardDTO.setNum(rs.getInt("num"));
				boardDTO.setWriter(rs.getString("writer"));
				boardDTO.setTitle(rs.getString("title"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRegdate(rs.getString("regdate"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setRe_step(rs.getInt("re_step"));
				boardDTO.setRe_level(rs.getInt("re_level"));
				boardDTO.setReadcount(rs.getInt("readcount"));
				boardDTO.setIp(rs.getString("ip"));
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
		return boardDTO;
	}//getContent()
	
}//class
