package com.accp.vo.lwx.merchant;

import java.sql.Timestamp;

public class ServicesVo {
		private int serviceid;
		private int stid;
		private int userid;
		private String stname;
		private String servicecoverimg;
		private String serviceimgurlone;
		private String serviceimgurltwo;
		private String serviceimgurlthree;
		private String serviceimgurlfour;
		private String name;//地区名称
		private String servicecity;
		private String servicecosttypeid;
		private String servicecostinclude;
		private String servicetitle;
		private String servicefutitle;
		private String serviceintro;
		private String uploadtitle;
		private String uploaddataurl;
		private  Timestamp  servicestartdate;
		private  Timestamp  serviceenddate;
		private int serviceprice;
		private int number;
		private  Timestamp  releasetime;
		private int auditstatus;
		public int getServiceid() {
			return serviceid;
		}
		public void setServiceid(int serviceid) {
			this.serviceid = serviceid;
		}
		public int getStid() {
			return stid;
		}
		public void setStid(int stid) {
			this.stid = stid;
		}
		public String getStname() {
			return stname;
		}
		public void setStname(String stname) {
			this.stname = stname;
		}
		public String getServicecoverimg() {
			return servicecoverimg;
		}
		public void setServicecoverimg(String servicecoverimg) {
			this.servicecoverimg = servicecoverimg;
		}
		public String getServicetitle() {
			return servicetitle;
		}
		public void setServicetitle(String servicetitle) {
			this.servicetitle = servicetitle;
		}
		public Timestamp getServicestartdate() {
			return servicestartdate;
		}
		public void setServicestartdate(Timestamp servicestartdate) {
			this.servicestartdate = servicestartdate;
		}
		public Timestamp getServiceenddate() {
			return serviceenddate;
		}
		public void setServiceenddate(Timestamp serviceenddate) {
			this.serviceenddate = serviceenddate;
		}
		public int getServiceprice() {
			return serviceprice;
		}
		public void setServiceprice(int serviceprice) {
			this.serviceprice = serviceprice;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public int getAuditstatus() {
			return auditstatus;
		}
		public void setAuditstatus(int auditstatus) {
			this.auditstatus = auditstatus;
		}
		
		public Timestamp getReleasetime() {
			return releasetime;
		}
		public void setReleasetime(Timestamp releasetime) {
			this.releasetime = releasetime;
		}
		
		public String getServiceimgurlone() {
			return serviceimgurlone;
		}
		public void setServiceimgurlone(String serviceimgurlone) {
			this.serviceimgurlone = serviceimgurlone;
		}
		public String getServiceimgurltwo() {
			return serviceimgurltwo;
		}
		public void setServiceimgurltwo(String serviceimgurltwo) {
			this.serviceimgurltwo = serviceimgurltwo;
		}
		public String getServiceimgurlthree() {
			return serviceimgurlthree;
		}
		public void setServiceimgurlthree(String serviceimgurlthree) {
			this.serviceimgurlthree = serviceimgurlthree;
		}
		public String getServiceimgurlfour() {
			return serviceimgurlfour;
		}
		public void setServiceimgurlfour(String serviceimgurlfour) {
			this.serviceimgurlfour = serviceimgurlfour;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getServicecity() {
			return servicecity;
		}
		public void setServicecity(String servicecity) {
			this.servicecity = servicecity;
		}
		public String getServicecosttypeid() {
			return servicecosttypeid;
		}
		public void setServicecosttypeid(String servicecosttypeid) {
			this.servicecosttypeid = servicecosttypeid;
		}
		public String getServicecostinclude() {
			return servicecostinclude;
		}
		public void setServicecostinclude(String servicecostinclude) {
			this.servicecostinclude = servicecostinclude;
		}
		public String getUploadtitle() {
			return uploadtitle;
		}
		public void setUploadtitle(String uploadtitle) {
			this.uploadtitle = uploadtitle;
		}
		public String getUploaddataurl() {
			return uploaddataurl;
		}
		public void setUploaddataurl(String uploaddataurl) {
			this.uploaddataurl = uploaddataurl;
		}
		
		public String getServicefutitle() {
			return servicefutitle;
		}
		public void setServicefutitle(String servicefutitle) {
			this.servicefutitle = servicefutitle;
		}
		
		public String getServiceintro() {
			return serviceintro;
		}
		public void setServiceintro(String serviceintro) {
			this.serviceintro = serviceintro;
		}
		
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		public ServicesVo() {
			// TODO Auto-generated constructor stub
		}
		public ServicesVo(int serviceid, int stid, int userid, String stname, String servicecoverimg,
				String serviceimgurlone, String serviceimgurltwo, String serviceimgurlthree, String serviceimgurlfour,
				String name, String servicecity, String servicecosttypeid, String servicecostinclude,
				String servicetitle, String servicefutitle, String serviceintro, String uploadtitle,
				String uploaddataurl, Timestamp servicestartdate, Timestamp serviceenddate, int serviceprice,
				int number, Timestamp releasetime, int auditstatus) {
			super();
			this.serviceid = serviceid;
			this.stid = stid;
			this.userid = userid;
			this.stname = stname;
			this.servicecoverimg = servicecoverimg;
			this.serviceimgurlone = serviceimgurlone;
			this.serviceimgurltwo = serviceimgurltwo;
			this.serviceimgurlthree = serviceimgurlthree;
			this.serviceimgurlfour = serviceimgurlfour;
			this.name = name;
			this.servicecity = servicecity;
			this.servicecosttypeid = servicecosttypeid;
			this.servicecostinclude = servicecostinclude;
			this.servicetitle = servicetitle;
			this.servicefutitle = servicefutitle;
			this.serviceintro = serviceintro;
			this.uploadtitle = uploadtitle;
			this.uploaddataurl = uploaddataurl;
			this.servicestartdate = servicestartdate;
			this.serviceenddate = serviceenddate;
			this.serviceprice = serviceprice;
			this.number = number;
			this.releasetime = releasetime;
			this.auditstatus = auditstatus;
		}
		
		

		
		
		
		
		
}
