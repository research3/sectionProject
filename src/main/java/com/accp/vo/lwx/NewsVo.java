package com.accp.vo.lwx;

import java.util.Date;

import com.accp.pojo.User;

public class NewsVo {
	private Integer newsid;

    private Integer thesender;

    private Integer addressee;

    private Date sendingtime;

    private Boolean readstate;

    private Integer newstype;

    private Integer messagegroup;

    private String content;
    
    private User Sender;
    
    private User Ressee;

	public NewsVo(Integer newsid, Integer thesender, Integer addressee, Date sendingtime, Boolean readstate,
			Integer newstype, Integer messagegroup, String content, User sender, User ressee) {
		super();
		this.newsid = newsid;
		this.thesender = thesender;
		this.addressee = addressee;
		this.sendingtime = sendingtime;
		this.readstate = readstate;
		this.newstype = newstype;
		this.messagegroup = messagegroup;
		this.content = content;
		Sender = sender;
		Ressee = ressee;
	}

	public NewsVo() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public Integer getNewsid() {
		return newsid;
	}

	public void setNewsid(Integer newsid) {
		this.newsid = newsid;
	}

	public Integer getThesender() {
		return thesender;
	}

	public void setThesender(Integer thesender) {
		this.thesender = thesender;
	}

	public Integer getAddressee() {
		return addressee;
	}

	public void setAddressee(Integer addressee) {
		this.addressee = addressee;
	}

	public Date getSendingtime() {
		return sendingtime;
	}

	public void setSendingtime(Date sendingtime) {
		this.sendingtime = sendingtime;
	}

	public Boolean getReadstate() {
		return readstate;
	}

	public void setReadstate(Boolean readstate) {
		this.readstate = readstate;
	}

	public Integer getNewstype() {
		return newstype;
	}

	public void setNewstype(Integer newstype) {
		this.newstype = newstype;
	}

	public Integer getMessagegroup() {
		return messagegroup;
	}

	public void setMessagegroup(Integer messagegroup) {
		this.messagegroup = messagegroup;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getSender() {
		return Sender;
	}

	public void setSender(User sender) {
		Sender = sender;
	}

	public User getRessee() {
		return Ressee;
	}

	public void setRessee(User ressee) {
		Ressee = ressee;
	}
}
