package com.hssj.pojo;

public class Link {
	private int lid;
	private String lname;
	private int lrow;
	private int cid_link;

	public Link() {
		super();
	}

	public Link(int lid, String lname, int lrow, int cid_link) {
		super();
		this.lid = lid;
		this.lname = lname;
		this.lrow = lrow;
		this.cid_link = cid_link;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getLrow() {
		return lrow;
	}

	public void setLrow(int lrow) {
		this.lrow = lrow;
	}

	public int getCid_link() {
		return cid_link;
	}

	public void setCid_link(int cid_link) {
		this.cid_link = cid_link;
	}

	@Override
	public String toString() {
		return "Link [lid=" + lid + ", lname=" + lname + ", lrow=" + lrow + ", cid_link=" + cid_link + "]";
	}

}
