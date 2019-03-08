package com.accp.biz.lwx;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lwx.MerchantEnterAndServiceDao;
import com.accp.pojo.Advertisement;
import com.accp.pojo.Appraisalapply;
import com.accp.pojo.Complainttype;
import com.accp.pojo.Languagetype;
import com.accp.pojo.Majortype;
import com.accp.pojo.Post;
import com.accp.pojo.Resouroe;
import com.accp.pojo.Servicecollection;
import com.accp.pojo.Servicedes;
import com.accp.pojo.Servicelevel;
import com.accp.pojo.Services;
import com.accp.pojo.Servicetype;
import com.accp.pojo.Sharea;
import com.accp.pojo.User;
import com.accp.vo.lwx.merchant.EvaluationVo;
import com.accp.vo.lwx.merchant.ServicesVo;
import com.accp.vo.lwx.merchant.UserAppVo;
import com.accp.vo.lwx.service.AdvertisementVO;
import com.accp.vo.lwx.service.EsLevelVO;
import com.accp.vo.lwx.service.HomePostVO;
import com.accp.vo.lwx.service.SameServiceVO;
import com.accp.vo.lwx.service.SerRecommendVO;
import com.accp.vo.lwx.service.SerReserveVO;
import com.accp.vo.lwx.service.ServiceDetailInfo;
import com.accp.vo.lwx.service.ServiceMerchantInfo;
import com.accp.vo.lwx.service.ServiceSelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class MerchantEnterAndServiceBiz {

	// @Transactional(propagation = Propagation.REQUIRED, isolation =
	// Isolation.READ_COMMITTED, readOnly = false)
	@Autowired
	private MerchantEnterAndServiceDao dao;

	public User queryUserinfo(String username) {
		return dao.queryUserinfo(username);
	}

	public User queryUserByid(Integer userid) {
		return dao.queryUserByid(userid);
	}

	public List<UserAppVo> queryAppraisalapply(int userid, int oneid, int twoid) {
		return dao.queryAppraisalapply(userid, oneid, twoid);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void addAppraisalapply(Appraisalapply app) {

		dao.addAppraisalapply(app);

	}

	public PageInfo<ServicesVo> queryServicesBytitle(Integer pageNum, Integer pageSize, String serviceTitle,
			Integer userid) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<ServicesVo>(dao.queryServiceByTitle(serviceTitle, userid));
	}

	public PageInfo<ServicesVo> queryServices(Integer pageNum, Integer pageSize, Integer userid) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<ServicesVo>(dao.queryServiceByTitle(null, userid));
	}

	public Services queryServices(int serviceid) {
		return dao.queryService(serviceid);

	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void addService(Services service) {

		dao.addService(service);

	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void removeService(int serviceID) {

		dao.deleteService(serviceID);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void modifyService(Services service) {

		dao.updateService(service);
	}

	public PageInfo<EvaluationVo> queryEvaluation(Integer pageNum, Integer pageSize, Integer userid) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<EvaluationVo>(dao.queryEvaluation(userid));
	}

	/**
	 * 商家入驻
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public int merchantMove(User user, float bond) {
		return dao.merchantMove(user, bond);
	}

	/**
	 * 根据服务列表条件查询服务
	 * 
	 * @return
	 */
	public PageInfo queryServices(ServiceSelect obj, int num, int size) {
		PageHelper.startPage(num, size);
		return new PageInfo(dao.queryServices(obj));
	}

	/**
	 * 查询服务类别
	 * 
	 * @param stpid
	 *            服务类别父级编号，为空则代表查询最高级服务类别
	 * @return
	 */
	public List<Servicetype> queryServiceType(Integer stpid, Integer selectNum) {
		return dao.queryServiceType(stpid, selectNum);
	}

	/**
	 * 根据一级服务类别查询对应的级别
	 * 
	 * @param stpid
	 * @return
	 */
	public List<Servicelevel> queryServicelevel(Integer stid) {
		return dao.queryServicelevel(stid);
	}

	/**
	 * 查询服务语言
	 * 
	 * @return
	 */
	public List<Languagetype> queryLanguagetype() {
		return dao.queryLanguagetype();
	}

	/**
	 * 查询擅长专业
	 * 
	 * @return
	 */
	public List<Majortype> queryMajortype() {
		return dao.queryMajortype();
	}

	/**
	 * 查询中韩行政地区地址信息
	 * 
	 * @param pid
	 *            地址编号
	 * @return
	 */
	public List<Sharea> querySharea(Integer pid, boolean flag) {
		return dao.querySharea(pid, flag);
	}

	/**
	 * 查询商家入驻需缴纳保证金金额
	 * 
	 * @return
	 */
	public float queryBond() {
		return dao.queryBond();
	}

	/**
	 * 查询服务的商家信息
	 */
	public ServiceMerchantInfo queryServiceMerchantInfo(Integer uid, Integer sid) {
		return dao.queryServiceMerchantInfo(uid, sid);
	}

	/**
	 * 查询服务详情信息
	 * 
	 * @param sid
	 *            服务编号
	 * @return
	 */
	public ServiceDetailInfo queryServiceDetailInfo(Integer sid) {
		return dao.queryServiceDetailInfo(sid);
	}

	/**
	 * 查询服务描述
	 * 
	 * @param sid
	 *            服务编号
	 * @return
	 */
	public List<Servicedes> queryServiceDes(Integer sid) {
		return dao.queryServiceDes(sid);
	}

	/**
	 * 查询服务评价
	 * 
	 * @param sid
	 * @return
	 */
	public PageInfo queryEvaluationserviceVO(Integer num, Integer size, Integer sid) {
		PageHelper.startPage(num, size);
		return new PageInfo(dao.queryEvaluationserviceVO(sid));
	}

	/**
	 * 查询服务评价星级人数
	 * 
	 * @param sid
	 * @return
	 */
	public EsLevelVO queryEsLevelVO(Integer sid) {
		return dao.queryEsLevelVO(sid);
	}

	/**
	 * 查询同城同类型服务
	 * 
	 * @param sid
	 * @return
	 */
	public List<SameServiceVO> querySameServiceVO(Integer sid) {
		return dao.querySameServiceVO(sid);
	}

	/**
	 * 查询举报原因
	 */
	public List<Complainttype> queryComplainttype() {
		return dao.queryComplainttype();
	}

	/**
	 * 进入服务详情添加浏览数
	 * 
	 * @param sid
	 * @return
	 */
	public int updateServiceBrowseNumber(Integer sid) {
		return dao.updateServiceBrowseNumber(sid);
	}

	/**
	 * 举报商家
	 * 
	 * @param obj
	 * @return
	 */
	public int saveServiceReport(Integer businessID, Integer serviceID, Integer loginUserID, Integer ctID) {
		return dao.saveServiceReport(businessID, serviceID, loginUserID, ctID);
	}

	/**
	 * 查询资料类别
	 * 
	 * @return
	 */
	public List<Resouroe> queryResouroe() {
		return dao.queryResouroe();
	}

	/**
	 * 提交预定
	 * 
	 * @return
	 */
	public int submitReserve(SerReserveVO obj) {
		return dao.submitReserve(obj);
	}

	/**
	 * 验证用户是否已经收藏过该商品
	 * 
	 * @param uid
	 * @param sid
	 * @return
	 */
	public Servicecollection queryUserSerCollectionCheck(Integer uid, Integer sid) {
		return dao.queryUserSerCollectionCheck(uid, sid);
	}

	/**
	 * 取消收藏
	 * 
	 * @param uid
	 *            用户编号
	 * @param sid
	 *            服务编号
	 * @return
	 */
	public int deleteSerCollection(Integer uid, Integer sid) {
		return dao.deleteSerCollection(uid, sid);
	}

	/**
	 * 添加收藏
	 * 
	 * @param uid
	 *            用户编号
	 * @param sid
	 *            服务编号
	 * @return
	 */
	public int saveSerCollection(Integer uid, Integer sid) {
		return dao.saveSerCollection(uid, sid);
	}

	/**
	 * 首页社区服务广告位查询
	 * 
	 * @param flag
	 * @return
	 */
	public List<AdvertisementVO> queryHomeAdvertising(Integer flag) {
		return dao.queryHomeAdvertising(flag);
	}

	/**
	 * 首页社区服务星级服务推荐商家
	 * 
	 * @param stid
	 * @return
	 */
	public List<SerRecommendVO> querySerRecommendVO(Integer stid) {
		return dao.querySerRecommendVO(stid);
	}

	/**
	 * 查询首页社区服务最新帖子
	 * 
	 * @return
	 */
	public List<HomePostVO> queryHomePostVO() {
		return dao.queryHomePostVO();
	}

	/**
	 * 查询首页社区服务美妆天地板块帖子
	 * 
	 * @return
	 */
	public List<Post> queryHomePostByMakeup() {
		return dao.queryHomePostByMakeup();
	}

	/**
	 * 新增金币流向记录
	 * 
	 * @param userID
	 *            用户编号
	 * @param acquisitionMode
	 *            流向类别
	 * @param recordDescribe
	 *            记录描述
	 * @param recordInAndOut
	 *            收入支出
	 * @param auditStatus
	 *            记录状态
	 * @return
	 */
	public int saveGoldNotes(Integer userID, Integer acquisitionMode, String recordDescribe, float recordInAndOut,
			Integer auditStatus) {
		return dao.saveGoldNotes(userID, acquisitionMode, recordDescribe, recordInAndOut, auditStatus);
	}
}
