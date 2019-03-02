package com.accp.pojo;

import java.util.Date;

public class Love {
	private int lid;
	private int userid;
	private int stid;
	private int typeid;
	private int qbid;
	private int num;
	private Date time;
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getQbid() {
		return qbid;
	}
	public void setQbid(int qbid) {
		this.qbid = qbid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Love(int lid, int userid, int stid, int typeid, int qbid, int num, Date time) {
		super();
		this.lid = lid;
		this.userid = userid;
		this.stid = stid;
		this.typeid = typeid;
		this.qbid = qbid;
		this.num = num;
		this.time = time;
	}
	public Love(int userid, int stid, int typeid, int qbid, int num, Date time) {
		super();
		this.userid = userid;
		this.stid = stid;
		this.typeid = typeid;
		this.qbid = qbid;
		this.num = num;
		this.time = time;
	}
	
	public Love() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Love [lid=" + lid + ", userid=" + userid + ", stid=" + stid + ", typeid=" + typeid + ", qbid=" + qbid
				+ ", num=" + num + ", time=" + time + "]";
	}
	
}
