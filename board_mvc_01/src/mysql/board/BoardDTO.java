package mysql.board;
import java.util.*;

public class BoardDTO {
	private int num; //�۹�ȣ
	private String writer; //�۾���
	private String title; //������
	private String regdate; //�����
	private String modifydate; //������
	private int readcount; //��ȸ��
	private int ref; //�۱׷�
	private int re_step; //���۰� ��� ����
	private int re_level; //��� ����
	private String content; //�۳���
	private String ip; //������
	private int rank; //���
	
	public BoardDTO(){}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}//class
