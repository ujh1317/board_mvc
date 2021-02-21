package notice;
import java.util.*;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;

import com.mysql.jdbc.PreparedStatement;

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
	
	
}//class
