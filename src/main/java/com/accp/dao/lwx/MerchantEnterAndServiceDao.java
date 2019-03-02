package com.accp.dao.lwx;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.pojo.Advertisement;
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
import com.accp.vo.lwx.service.AdvertisementVO;
import com.accp.vo.lwx.service.EsLevelVO;
import com.accp.vo.lwx.service.EvaluationserviceVO;
import com.accp.vo.lwx.service.HomePostVO;
import com.accp.vo.lwx.service.SameServiceVO;
import com.accp.vo.lwx.service.SerRecommendVO;
import com.accp.vo.lwx.service.SerReserveVO;
import com.accp.vo.lwx.service.ServiceDetailInfo;
import com.accp.vo.lwx.service.ServiceMerchantInfo;
import com.accp.vo.lwx.service.ServiceSelect;

/**
 * 商家入驻和服务Dao层
 * @author lsm
 *
 */
public interface MerchantEnterAndServiceDao {
	/**
	 * 查询服务类别
	 * @param stpid 服务类别父级编号，为空则代表查询最高级服务类别
	 * @return
	 */
	public List<Servicetype> queryServiceType(@Param("stpid")Integer stpid,@Param("selectNum")Integer selectNum);
	/**
	 * 根据一级服务类别查询对应的级别
	 * @param stpid
	 * @return
	 */
	public List<Servicelevel> queryServicelevel(@Param("stid")Integer stid);
	/**
	 * 根据服务列表条件查询服务
	 * @return
	 */
	public List<Services> queryServices(@Param("obj")ServiceSelect obj);
	/**
	 * 查询服务语言
	 * @return
	 */
	public List<Languagetype> queryLanguagetype();
	/**
	 * 查询擅长专业
	 * @return
	 */
	public List<Majortype> queryMajortype();
	/**
	 * 查询中韩行政地区地址信息
	 * @param pid 地址编号
	 * @return
	 */
	public List<Sharea> querySharea(@Param("pid")Integer pid,@Param("flag")boolean flag);
	/**
	 * 商家入驻
	 * @return
	 */
	public int merchantMove(@Param("obj")User user,@Param("bond")float bond);
	/**
	 * 查询商家入驻需缴纳保证金金额
	 * @return
	 */
	public float queryBond();
	/**
	 * 查询服务的商家信息
	 */
	public ServiceMerchantInfo queryServiceMerchantInfo(@Param("uid")Integer uid,@Param("sid")Integer sid);
	/**
	 * 查询服务详情信息
	 * @param sid 服务编号
	 * @return
	 */
	public ServiceDetailInfo queryServiceDetailInfo(@Param("sid")Integer sid);
	/**
	 * 查询服务描述
	 * @param sid 服务编号
	 * @return
	 */
	public List<Servicedes> queryServiceDes(@Param("sid")Integer sid);
	/**
	 * 查询服务评价
	 * @param sid
	 * @return
	 */
	public List<EvaluationserviceVO> queryEvaluationserviceVO(@Param("sid")Integer sid);
	/**
	 * 查询服务评价星级人数
	 * @param sid
	 * @return
	 */
	public EsLevelVO queryEsLevelVO(@Param("sid")Integer sid);
	/**
	 * 查询同城同类型服务
	 * @param sid
	 * @return
	 */
	public List<SameServiceVO> querySameServiceVO(@Param("sid")Integer sid);
	/**
	 * 查询举报原因
	 */
	public List<Complainttype> queryComplainttype();
	/**
	 * 进入服务详情添加浏览数
	 * @param sid
	 * @return
	 */
	public int updateServiceBrowseNumber(@Param("sid")Integer sid);
	/**
	 * 举报商家
	 * @param obj
	 * @return
	 */
	public int saveServiceReport(@Param("businessID")Integer businessID,@Param("serviceID")Integer serviceID,@Param("loginUserID")Integer loginUserID,@Param("ctID")Integer ctID);
	/**
	 * 查询资料类别
	 * @return
	 */
	public List<Resouroe> queryResouroe();
	
	/**
	 * 提交预定
	 * @return
	 */
	public int submitReserve(@Param("obj")SerReserveVO obj);
	/**
	 * 验证用户是否已经收藏过该商品
	 * @param uid
	 * @param sid
	 * @return
	 */
	public Servicecollection queryUserSerCollectionCheck(@Param("uid")Integer uid,@Param("sid")Integer sid);
	/**
	 * 取消收藏
	 * @param uid	用户编号
	 * @param sid	服务编号
	 * @return
	 */
	public int deleteSerCollection(@Param("uid")Integer uid,@Param("sid")Integer sid);
	/**
	 * 添加收藏
	 * @param uid	用户编号
	 * @param sid	服务编号
	 * @return
	 */
	public int saveSerCollection(@Param("uid")Integer uid,@Param("sid")Integer sid);
	/**
	 * 首页社区服务广告位查询
	 * @param flag
	 * @return
	 */
	public List<AdvertisementVO> queryHomeAdvertising(@Param("flag")Integer flag);
	/**
	 * 首页社区服务星级服务推荐商家
	 * @param stid
	 * @return
	 */
	public List<SerRecommendVO> querySerRecommendVO(@Param("stid")Integer stid);
	/**
	 * 查询首页社区服务最新帖子
	 * @return
	 */
	public List<HomePostVO> queryHomePostVO();
	/**
	 * 查询首页社区服务美妆天地板块帖子
	 * @return
	 */
	public List<Post> queryHomePostByMakeup();
	/**
	 * 新增金币流向记录
	 * @param userID 用户编号
	 * @param acquisitionMode 流向类别
	 * @param recordDescribe 记录描述
	 * @param recordInAndOut 收入支出
	 * @param auditStatus 记录状态
	 * @return
	 */
	public int saveGoldNotes(@Param("userID")Integer userID,@Param("acquisitionMode")Integer acquisitionMode,@Param("recordDescribe")String recordDescribe,@Param("recordInAndOut")float recordInAndOut,@Param("auditStatus")Integer auditStatus);
}
