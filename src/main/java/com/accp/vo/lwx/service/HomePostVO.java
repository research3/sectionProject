package com.accp.vo.lwx.service;

public class HomePostVO {
	private Integer postID;	//帖子编号
	private String title;	//标题
	private Integer userID;	//用户编号
	private String userName;	//用户名
	private String userImgPath;	//用户头像
	private String fmName;	//帖子类别
	private String releaseTime;	//发布时间
	private Integer replyUserID;	//最新回复用户编号
	private String replyUserName;	//最新回复用户名
	private String replyUserTime;//最新回复时间
	public Integer getPostID() {
		return postID;
	}
	public void setPostID(Integer postID) {
		this.postID = postID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImgPath() {
		return userImgPath;
	}
	public void setUserImgPath(String userImgPath) {
		this.userImgPath = userImgPath;
	}
	public String getFmName() {
		return fmName;
	}
	public void setFmName(String fmName) {
		this.fmName = fmName;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public Integer getReplyUserID() {
		return replyUserID;
	}
	public void setReplyUserID(Integer replyUserID) {
		this.replyUserID = replyUserID;
	}
	public String getReplyUserName() {
		return replyUserName;
	}
	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}
	public String getReplyUserTime() {
		return replyUserTime;
	}
	public void setReplyUserTime(String replyUserTime) {
		this.replyUserTime = replyUserTime;
	}
}
