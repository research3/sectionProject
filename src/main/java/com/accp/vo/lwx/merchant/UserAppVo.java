package com.accp.vo.lwx.merchant;

import java.util.Date;

public class UserAppVo {
		private int stid;
		private  String   stname;
		private Date submittime;
		private Date audittime;
		private int auditstatus;
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
		public Date getSubmittime() {
			return submittime;
		}
		public void setSubmittime(Date submittime) {
			this.submittime = submittime;
		}
		public Date getAudittime() {
			return audittime;
		}
		public void setAudittime(Date audittime) {
			this.audittime = audittime;
		}
		public int getAuditstatus() {
			return auditstatus;
		}
		public void setAuditstatus(int auditstatus) {
			this.auditstatus = auditstatus;
		}
	
		
		
}
