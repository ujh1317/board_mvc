package mysql.board;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class BoardDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	//�̱��� ��ü����
	private static BoardDAO boardDAO = new BoardDAO();
	
	//jsp���� dao��ü ���
	public static BoardDAO getBoardDAO(){
		return boardDAO;
	}//getDao()
	
	private BoardDAO(){}
	
	//Ŀ�ؼ�
	private Connection getConn() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getConn()
	
	//�۾��� , ��۾���---------------------------------------------------------
	public void getInsert(BoardDTO boardDTO) throws Exception{
		int num = boardDTO.getNum();
		int ref = boardDTO.getRef();
		int re_step = boardDTO.getRe_step();
		int re_level = boardDTO.getRe_level();
		
		int number = 0;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select max(num) from board");
			rs = pstmt.executeQuery();
			if(rs.next()){//���� ������
				number = rs.getInt(1)+1;
			}else{//���� ������
				number = 1;
			}//else
			
			if(num!=0){
				sql = "update board set re_step=re_step+2 where ref=? and re_step>?";
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
			
			sql = "insert into board(writer,title,regdate,modifydate,ref,re_step,re_level,content,ip,rank)"
					+" values(?,?,now(),now(),?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getWriter());
			pstmt.setString(2, boardDTO.getTitle());
			pstmt.setInt(3, ref);
			pstmt.setInt(4, re_step);
			pstmt.setInt(5, re_level);
			pstmt.setString(6, boardDTO.getContent());
			pstmt.setString(7, boardDTO.getIp());
			pstmt.setInt(8, boardDTO.getRank());
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
	}//getInsert()
	
	//�۰���---------------------------------------------------------
	public int getCount() throws Exception{
		int x = 0;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			if(rs.next()){
				x = rs.getInt(1); //�� �۰���
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
	
	//����Ʈ---------------------------------------------------------
	public List getList(int start, int cnt) throws Exception{
		List<BoardDTO> list = null;
		try{
			conn = getConn();
			sql = "select * from board order by ref desc, re_step asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start-1); //������ġ
			pstmt.setInt(2, cnt); //����
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
					boardDTO.setModifydate(rs.getString("modifydate"));
					boardDTO.setReadcount(rs.getInt("readcount"));
					boardDTO.setRef(rs.getInt("ref"));
					boardDTO.setRe_step(rs.getInt("re_step"));
					boardDTO.setRe_level(rs.getInt("re_level"));
					boardDTO.setIp(rs.getString("ip"));
					boardDTO.setRank(rs.getInt("rank"));
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
	
	//�۳��뺸��---------------------------------------------------------
	public BoardDTO getContent(int num) throws Exception{
		BoardDTO boardDTO = null;
		try{
			conn = getConn();
			sql = "update board set readcount=readcount+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				boardDTO = new BoardDTO();
				boardDTO.setNum(rs.getInt("num"));
				boardDTO.setWriter(rs.getString("writer"));
				boardDTO.setTitle(rs.getString("title"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRegdate(rs.getString("regdate"));
				boardDTO.setModifydate(rs.getString("modifydate"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setRe_step(rs.getInt("re_step"));
				boardDTO.setRe_level(rs.getInt("re_level"));
				boardDTO.setReadcount(rs.getInt("readcount"));
				boardDTO.setIp(rs.getString("ip"));
				boardDTO.setRank(rs.getInt("rank"));
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
	
	//�ۼ���---------------------------------------------------------
	public BoardDTO getUpdate(int num) throws Exception{
		BoardDTO boardDTO = null;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				boardDTO = new BoardDTO();
				boardDTO.setNum(rs.getInt("num"));
				boardDTO.setWriter(rs.getString("writer"));
				boardDTO.setTitle(rs.getString("title"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setReadcount(rs.getInt("readcount"));
				boardDTO.setRegdate(rs.getString("regdate"));
				boardDTO.setModifydate(rs.getString("modifydate"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setRe_step(rs.getInt("re_step"));
				boardDTO.setRe_level(rs.getInt("re_level"));
				boardDTO.setIp(rs.getString("ip"));
				boardDTO.setRank(rs.getInt("rank"));
			}//while
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
	}//getUpdate()
	
	//DB����---------------------------------------------------------
	public int getUpdateDb(BoardDTO boardDTO) throws Exception{
		int x = -10;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, boardDTO.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()){
				sql = "update board set title=?, content=?, rank=?, modifydate=now() where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardDTO.getTitle());
				pstmt.setString(2, boardDTO.getContent());
				pstmt.setInt(3, boardDTO.getRank());
				pstmt.setInt(4, boardDTO.getNum());
				pstmt.executeUpdate();
				x = 1;
			}else{
				x = -1;
			}//else
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){rs.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return x;
	}//getUpdateDb()
	
	//�ۻ���---------------------------------------------------------
	public int getDelete(int num) throws Exception{
		int x = -10;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select writer from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pstmt = conn.prepareStatement("delete from board where num=?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				x = 1;
			}else{
				x = -1;
			}//else
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
	}//getDelete()
}//class
