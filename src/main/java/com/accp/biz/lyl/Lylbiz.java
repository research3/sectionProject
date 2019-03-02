/**
 * 
 */
package com.accp.biz.lyl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lyl.LylDao;
import com.accp.pojo.Complainttype;
import com.accp.pojo.Servicedes;
import com.accp.pojo.Servicelevel;
import com.accp.pojo.Services;
import com.accp.pojo.Servicetype;
import com.accp.pojo.Sharea;
import com.accp.vo.lyl.ErjixxVo;
import com.accp.vo.lyl.ErjiyemcxVo;
import com.accp.vo.lyl.EsLevelVO;
import com.accp.vo.lyl.EvaluationserviceVO;
import com.accp.vo.lyl.SameServiceVO;
import com.accp.vo.lyl.ServiceDetailInfo;
import com.accp.vo.lyl.ServiceMerchantInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ch.qos.logback.core.net.SyslogOutputStream;

/**   
* 项目名称：HanTingProject   
* 类名称：Lylbiz   
* 类描述：   
* 创建人：刘应龙 
* 创建时间：2019年2月20日
* @version        
*/
@Service("Lylbiz")
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class Lylbiz {
	@Autowired
	private LylDao dao;
	
	public PageInfo<ErjixxVo> erjquery(int pageNum,int pageSize,ErjiyemcxVo vo){
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<ErjixxVo>(dao.erjquery(vo));
	}
	public List<Sharea> queryCountry(int pid) {
		return dao.queryCountry(pid);
	}
	public List<Servicetype> queryserType(int stPid){
		return dao.queryserType(stPid);
	}
	
	public List<Servicelevel> queryserLeve(int stid){
		return dao.queryserLeve(stid);
	}
	public ErjiyemcxVo queryzhin(int userid,int stid) {
		ErjiyemcxVo l=new ErjiyemcxVo();
		l.setCountry(dao.queryzhin(1, userid, stid).getQbid());//国家
		l.setResourceid(dao.queryzhin(2, userid, stid).getQbid());//类别
		l.setJib(dao.queryzhin(3, userid, stid).getQbid());//级别
		l.setGfrz(dao.queryzhin(4, userid, stid).getQbid());//官方认证
		return l;
	}
	
	
	public ServiceMerchantInfo queryServiceMerchantInfo(Integer uid,Integer sid) {
		return dao.queryServiceMerchantInfo(uid,sid);
	}
	
	public List<Servicedes> queryServiceDes(Integer sid){
		return dao.queryServiceDes(sid);
	}
	
	public PageInfo<EvaluationserviceVO> queryEvaluationserviceVO(Integer num,Integer size,Integer sid){
		PageHelper.startPage(num, size);
		return new PageInfo<EvaluationserviceVO>(dao.queryEvaluationserviceVO(sid));
	}
	public ServiceDetailInfo queryServiceDetailInfo(Integer sid){
		return dao.queryServiceDetailInfo(sid);
	}
	public EsLevelVO queryEsLevelVO(Integer sid){
		return dao.queryEsLevelVO(sid);
	}
	public List<SameServiceVO> querySameServiceVO(Integer sid){
		return dao.querySameServiceVO(sid);
	}
	public List<Complainttype> queryComplainttype(){
		return dao.queryComplainttype();
	}
	public void updateServiceBrowseNumber(Integer sid){
		dao.updateServiceBrowseNumber(sid);
	}
	
}
