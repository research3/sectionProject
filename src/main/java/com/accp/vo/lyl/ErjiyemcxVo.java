/**
 * 
 */
package com.accp.vo.lyl;

/**   
* 项目名称：HanTingProject   
* 类名称：ErjiyemcxVo   
* 类描述：   
* 创建人：刘应龙 
* 创建时间：2019年2月20日
* @version        
*/

public class ErjiyemcxVo {
	private int stid;//服务类型
	private String start;//开始时间
	private String end;//结束时间
	private int resourceid;//资源类别编号
	private int country;//国家编号
	private String city;//城市
	@Override
	public String toString() {
		return "ErjiyemcxVo [stid=" + stid + ", start=" + start + ", end=" + end + ", resourceid=" + resourceid
				+ ", country=" + country + ", city=" + city + ", jib=" + jib + ", maxjf=" + maxjf + ", minjf=" + minjf
				+ ", jiage=" + jiage + ", rqi=" + rqi + ", gfrz=" + gfrz + ", servicetitle=" + servicetitle + ", price="
				+ price + ", browse=" + browse + "]";
	}
	private int jib;//级别
	private int maxjf;//最大积分
	private int minjf;//最小积分
	private int jiage;//价格升序降序1升2降0无
	private int rqi;//人气1升2降0无
	private int gfrz;//1商家自营2官方认证
	private String servicetitle;//搜索
	private int price;//0无1升2降
	private int browse;//0无1升2降
	
	
	public int getJib() {
		return jib;
	}
	public void setJib(int jib) {
		this.jib = jib;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getBrowse() {
		return browse;
	}
	public void setBrowse(int browse) {
		this.browse = browse;
	}
	public String getServicetitle() {
		return servicetitle;
	}
	public void setServicetitle(String servicetitle) {
		this.servicetitle = servicetitle;
	}
	public int getGfrz() {
		return gfrz;
	}
	public void setGfrz(int gfrz) {
		this.gfrz = gfrz;
	}
	public int getRqi() {
		return rqi;
	}
	public void setRqi(int rqi) {
		this.rqi = rqi;
	}
	public int getJiage() {
		return jiage;
	}
	public void setJiage(int jiage) {
		this.jiage = jiage;
	}
	public ErjiyemcxVo(int stid, String start, String end, int resourceid, int country, String city, int maxjf,
			int minjf) {
		super();
		this.stid = stid;
		this.start = start;
		this.end = end;
		this.resourceid = resourceid;
		this.country = country;
		this.city = city;
		this.maxjf = maxjf;
		this.minjf = minjf;
	}
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getResourceid() {
		return resourceid;
	}
	public void setResourceid(int resourceid) {
		this.resourceid = resourceid;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getMaxjf() {
		return maxjf;
	}
	public void setMaxjf(int maxjf) {
		this.maxjf = maxjf;
	}
	public int getMinjf() {
		return minjf;
	}
	public void setMinjf(int minjf) {
		this.minjf = minjf;
	}
	public ErjiyemcxVo() {
		super();
	}
	
}
