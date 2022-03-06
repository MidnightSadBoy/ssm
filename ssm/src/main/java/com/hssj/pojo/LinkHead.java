package com.hssj.pojo;

public class LinkHead {
	private int lhid;
	private String lhname;
	private int cid_lhead;

	public LinkHead() {
		super();
	}

	public LinkHead(int lhid, String lhname, int cid_lhead) {
		super();
		this.lhid = lhid;
		this.lhname = lhname;
		this.cid_lhead = cid_lhead;
	}

	public int getLhid() {
		return lhid;
	}

	public void setLhid(int lhid) {
		this.lhid = lhid;
	}

	public String getLhname() {
		return lhname;
	}

	public void setLhname(String lhname) {
		this.lhname = lhname;
	}

	public int getCid_lhead() {
		return cid_lhead;
	}

	public void setCid_lhead(int cid_lhead) {
		this.cid_lhead = cid_lhead;
	}

	@Override
	public String toString() {
		return "LinkHead [lhid=" + lhid + ", lhname=" + lhname + ", cid_lhead=" + cid_lhead + "]";
	}

}
