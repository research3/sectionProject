/**
 * 
 */
package com.accp.vo.lyl;

import java.util.Date;

/**   
* 项目名称：HanTingProject   
* 类名称：Erjixx   
* 类描述：   
* 创建人：刘应龙 
* 创建时间：2019年2月20日
* @version        
*/

public class ErjixxVo {
	 private Integer serviceid;

	    private Integer stid;

	    private Integer userid;

	    private Integer resourceid;

	    private String servicetitle;

	    private String servicefutitle;

	    private String downloadtitle;

	    private Integer serviceprice;

	    private String servicecoverimg;

	    private String serviceimgurlone;

	    private String serviceimgurltwo;

	    private String serviceimgurlthree;

	    private String serviceimgurlfour;

	    private String servicecosttypeid;

	    private String serviceintro;

	    private String servicecity;

	    private Integer country;

	    private String servicecostinclude;

	    private Date servicestartdate;

	    private Date serviceenddate;

	    private String schoolregion;

	    private String schoolnamebycn;

	    private String majoynamebycn;

	    private String schoolnamebyrok;

	    private String majoynamebyrok;

	    private String goodatmajoy;

	    private String hospitalname;

	    private Integer servicehour;

	    private String uploaddataurl;

	    private Date releasetime;

	    private Integer browsenumber;

	    private Float weight;

	    private Boolean recommendbool;

	    private Integer shelfstate;

	    private Integer auditstatus;

	    private String adminopinion;
	    
	    private int shelfState;//上架下架
	    private int auditStatus;//审核状态
	    private int serviceIntegral;//积分
	    private int authentication;//是否官方鉴定
	    
	    private String shopname;//店铺名字
	    private String userimgpath;//图片
		public Integer getServiceid() {
			return serviceid;
		}
		public void setServiceid(Integer serviceid) {
			this.serviceid = serviceid;
		}
		public Integer getStid() {
			return stid;
		}
		public void setStid(Integer stid) {
			this.stid = stid;
		}
		public Integer getUserid() {
			return userid;
		}
		public void setUserid(Integer userid) {
			this.userid = userid;
		}
		public Integer getResourceid() {
			return resourceid;
		}
		public void setResourceid(Integer resourceid) {
			this.resourceid = resourceid;
		}
		public String getServicetitle() {
			return servicetitle;
		}
		public void setServicetitle(String servicetitle) {
			this.servicetitle = servicetitle;
		}
		public String getServicefutitle() {
			return servicefutitle;
		}
		public void setServicefutitle(String servicefutitle) {
			this.servicefutitle = servicefutitle;
		}
		public String getDownloadtitle() {
			return downloadtitle;
		}
		public void setDownloadtitle(String downloadtitle) {
			this.downloadtitle = downloadtitle;
		}
		public Integer getServiceprice() {
			return serviceprice;
		}
		public void setServiceprice(Integer serviceprice) {
			this.serviceprice = serviceprice;
		}
		public String getServicecoverimg() {
			return servicecoverimg;
		}
		public void setServicecoverimg(String servicecoverimg) {
			this.servicecoverimg = servicecoverimg;
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
		public String getServicecosttypeid() {
			return servicecosttypeid;
		}
		public void setServicecosttypeid(String servicecosttypeid) {
			this.servicecosttypeid = servicecosttypeid;
		}
		public String getServiceintro() {
			return serviceintro;
		}
		public void setServiceintro(String serviceintro) {
			this.serviceintro = serviceintro;
		}
		public String getServicecity() {
			return servicecity;
		}
		public void setServicecity(String servicecity) {
			this.servicecity = servicecity;
		}
		public Integer getCountry() {
			return country;
		}
		public void setCountry(Integer country) {
			this.country = country;
		}
		public String getServicecostinclude() {
			return servicecostinclude;
		}
		public void setServicecostinclude(String servicecostinclude) {
			this.servicecostinclude = servicecostinclude;
		}
		public Date getServicestartdate() {
			return servicestartdate;
		}
		public void setServicestartdate(Date servicestartdate) {
			this.servicestartdate = servicestartdate;
		}
		public Date getServiceenddate() {
			return serviceenddate;
		}
		public void setServiceenddate(Date serviceenddate) {
			this.serviceenddate = serviceenddate;
		}
		public String getSchoolregion() {
			return schoolregion;
		}
		public void setSchoolregion(String schoolregion) {
			this.schoolregion = schoolregion;
		}
		public String getSchoolnamebycn() {
			return schoolnamebycn;
		}
		public void setSchoolnamebycn(String schoolnamebycn) {
			this.schoolnamebycn = schoolnamebycn;
		}
		public String getMajoynamebycn() {
			return majoynamebycn;
		}
		public void setMajoynamebycn(String majoynamebycn) {
			this.majoynamebycn = majoynamebycn;
		}
		public String getSchoolnamebyrok() {
			return schoolnamebyrok;
		}
		public void setSchoolnamebyrok(String schoolnamebyrok) {
			this.schoolnamebyrok = schoolnamebyrok;
		}
		public String getMajoynamebyrok() {
			return majoynamebyrok;
		}
		public void setMajoynamebyrok(String majoynamebyrok) {
			this.majoynamebyrok = majoynamebyrok;
		}
		public String getGoodatmajoy() {
			return goodatmajoy;
		}
		public void setGoodatmajoy(String goodatmajoy) {
			this.goodatmajoy = goodatmajoy;
		}
		public String getHospitalname() {
			return hospitalname;
		}
		public void setHospitalname(String hospitalname) {
			this.hospitalname = hospitalname;
		}
		public Integer getServicehour() {
			return servicehour;
		}
		public void setServicehour(Integer servicehour) {
			this.servicehour = servicehour;
		}
		public String getUploaddataurl() {
			return uploaddataurl;
		}
		public void setUploaddataurl(String uploaddataurl) {
			this.uploaddataurl = uploaddataurl;
		}
		public Date getReleasetime() {
			return releasetime;
		}
		public void setReleasetime(Date releasetime) {
			this.releasetime = releasetime;
		}
		public Integer getBrowsenumber() {
			return browsenumber;
		}
		public void setBrowsenumber(Integer browsenumber) {
			this.browsenumber = browsenumber;
		}
		public Float getWeight() {
			return weight;
		}
		public void setWeight(Float weight) {
			this.weight = weight;
		}
		public Boolean getRecommendbool() {
			return recommendbool;
		}
		public void setRecommendbool(Boolean recommendbool) {
			this.recommendbool = recommendbool;
		}
		public Integer getShelfstate() {
			return shelfstate;
		}
		public void setShelfstate(Integer shelfstate) {
			this.shelfstate = shelfstate;
		}
		public Integer getAuditstatus() {
			return auditstatus;
		}
		public void setAuditstatus(Integer auditstatus) {
			this.auditstatus = auditstatus;
		}
		public String getAdminopinion() {
			return adminopinion;
		}
		public void setAdminopinion(String adminopinion) {
			this.adminopinion = adminopinion;
		}
		public int getShelfState() {
			return shelfState;
		}
		public void setShelfState(int shelfState) {
			this.shelfState = shelfState;
		}
		public int getAuditStatus() {
			return auditStatus;
		}
		public void setAuditStatus(int auditStatus) {
			this.auditStatus = auditStatus;
		}
		public int getServiceIntegral() {
			return serviceIntegral;
		}
		public void setServiceIntegral(int serviceIntegral) {
			this.serviceIntegral = serviceIntegral;
		}
		public int getAuthentication() {
			return authentication;
		}
		public void setAuthentication(int authentication) {
			this.authentication = authentication;
		}
		public String getShopname() {
			return shopname;
		}
		public void setShopname(String shopname) {
			this.shopname = shopname;
		}
		public String getUserimgpath() {
			return userimgpath;
		}
		public void setUserimgpath(String userimgpath) {
			this.userimgpath = userimgpath;
		}
		public ErjixxVo(Integer serviceid, Integer stid, Integer userid, Integer resourceid, String servicetitle,
				String servicefutitle, String downloadtitle, Integer serviceprice, String servicecoverimg,
				String serviceimgurlone, String serviceimgurltwo, String serviceimgurlthree, String serviceimgurlfour,
				String servicecosttypeid, String serviceintro, String servicecity, Integer country,
				String servicecostinclude, Date servicestartdate, Date serviceenddate, String schoolregion,
				String schoolnamebycn, String majoynamebycn, String schoolnamebyrok, String majoynamebyrok,
				String goodatmajoy, String hospitalname, Integer servicehour, String uploaddataurl, Date releasetime,
				Integer browsenumber, Float weight, Boolean recommendbool, Integer shelfstate, Integer auditstatus,
				String adminopinion, int shelfState2, int auditStatus2, int serviceIntegral, int authentication,
				String shopname, String userimgpath) {
			super();
			this.serviceid = serviceid;
			this.stid = stid;
			this.userid = userid;
			this.resourceid = resourceid;
			this.servicetitle = servicetitle;
			this.servicefutitle = servicefutitle;
			this.downloadtitle = downloadtitle;
			this.serviceprice = serviceprice;
			this.servicecoverimg = servicecoverimg;
			this.serviceimgurlone = serviceimgurlone;
			this.serviceimgurltwo = serviceimgurltwo;
			this.serviceimgurlthree = serviceimgurlthree;
			this.serviceimgurlfour = serviceimgurlfour;
			this.servicecosttypeid = servicecosttypeid;
			this.serviceintro = serviceintro;
			this.servicecity = servicecity;
			this.country = country;
			this.servicecostinclude = servicecostinclude;
			this.servicestartdate = servicestartdate;
			this.serviceenddate = serviceenddate;
			this.schoolregion = schoolregion;
			this.schoolnamebycn = schoolnamebycn;
			this.majoynamebycn = majoynamebycn;
			this.schoolnamebyrok = schoolnamebyrok;
			this.majoynamebyrok = majoynamebyrok;
			this.goodatmajoy = goodatmajoy;
			this.hospitalname = hospitalname;
			this.servicehour = servicehour;
			this.uploaddataurl = uploaddataurl;
			this.releasetime = releasetime;
			this.browsenumber = browsenumber;
			this.weight = weight;
			this.recommendbool = recommendbool;
			this.shelfstate = shelfstate;
			this.auditstatus = auditstatus;
			this.adminopinion = adminopinion;
			shelfState = shelfState2;
			auditStatus = auditStatus2;
			this.serviceIntegral = serviceIntegral;
			this.authentication = authentication;
			this.shopname = shopname;
			this.userimgpath = userimgpath;
		}
		public ErjixxVo() {
			super();
		}
	    
}
