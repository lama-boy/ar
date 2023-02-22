package entity;

import java.util.Date;

public class BoardVO {
	public int    num;
	public String title;
	public String content;
	public String writer;
	public Date   regdate;
	
	// 생성자
	public BoardVO() {}
	public BoardVO(int num, String title, String content, String writer, Date regdate) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
