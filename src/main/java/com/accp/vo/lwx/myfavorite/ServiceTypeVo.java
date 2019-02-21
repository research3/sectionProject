package com.accp.vo.lwx.myfavorite;

public class ServiceTypeVo {
	private Integer stid;
	private String stName;

	
	public Integer getStid() {
		return stid;
	}

	public void setStid(Integer stid) {
		this.stid = stid;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	@Override
	public String toString() {
		return "ServiceTypeVo [stid=" + stid + ", stName=" + stName + "]";
	}
	
}
