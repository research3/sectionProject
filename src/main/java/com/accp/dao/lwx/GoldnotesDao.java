package com.accp.dao.lwx;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.pojo.Banktype;
import com.accp.pojo.Evaluationservice;
import com.accp.pojo.Goldnotes;
import com.accp.pojo.Integralrecord;
import com.accp.pojo.Logistics;
import com.accp.pojo.News;
import com.accp.pojo.Putforward;
import com.accp.pojo.Services;
import com.accp.pojo.Sharea;
import com.accp.pojo.User;
import com.accp.vo.lwx.Evaluate.EvaluationserviceToservicesVo;
import com.accp.vo.lwx.myfavorite.UserToServicesVo;
import com.accp.vo.lwx.myfavorite.UserVo;


public interface GoldnotesDao {
	/**
	 * 根据 用户id查询金币流向表
	 * @param userId
	 * @return
	 */
    public List<Goldnotes>goldnotesQueryAll(@Param("userId")Integer userId,
    		@Param("acquisitionMode")Integer acquisitionMode);
    /**
     * 查询积分流向
     * @param userId
     * @return
     */
    public List<Integralrecord>IntegralRecordQueryAll(@Param("userId")Integer userId);
    /**
     *添加用户积分流向表
     * @param putforWard
     * @return
     */
    public int addPutforWard(@Param("putforWard") Putforward putforWard);
   /**
    *  添加用户金币支入支出记录
    * @param goldnotes
    * @return
    */
    public int  addGoldnotes(Goldnotes goldnotes);
    /**
     * 查询我的物流
     * @param logistics
     * @return
     */
    public List<Logistics> getListlogistics(@Param("logistics")Logistics logistics);
    /**
     * 查询我的服务商品评价记录
     * @param evaluationservice
     * @return
     */
    public List<EvaluationserviceToservicesVo>getListEvaluationService(@Param("evaluationservice")Evaluationservice evaluationservice);
    /**
     * 查询银行列表
     * @return 
     */
    public List<Banktype>getListBankType();
    /**
     * 添加物流记录 
     * @param logistics
     * @return
     */
    public int addLogistics(@Param("logistics")Logistics logistics);
    /**
     * 查询物流详情
     * @param id
     * @return
     */
    public Logistics getLogistics(@Param("userId")Integer  userId,@Param("id")Integer id);
    /**
     * 查询地址 
     * @param id
     * @return
     */
    public List<Sharea> getShAreaById(@Param("id")Integer id);
    /**
     * 查询用户余额
     * @param userId
     * @return
     */
    public User getUser (@Param("userId")Integer userId);
    /**
     * 修改用户金币余额
     * @param user
     * @return
     */
    public int updUser(@Param("moery")Float moery,@Param("userIntegral")Integer userIntegral,@Param("userId")Integer userId);
    /**
     *查询充值记录是否存在
     * @param logisticsId
     * @return
     */
    public Goldnotes getGoldnotesById(@Param("recordId")Integer recordId);
    /**
     * 修改充值记录状态
     * @return
     */
     public int updGoldnotes(@Param("recordId")Integer recordId,@Param("auditStatus")Integer auditStatus);
     /**
      * 查询当前用户收藏的商家
      * @param userId
      * @return
      */
     public List<UserVo>getMerchantCollectionById(@Param("userId")Integer userId);
     /**
      * 查询当前用户收藏的服务
      * @param userId
      * @return
      */
     public List<Services>getServicesByUserId(@Param("userId")Integer userId);
     /**
      * 修改物流
      * @param logistics
      * @return
      */
     public int updatedLogistics(@Param("logistics")Logistics logistics);
     /**
      * 查询系统推荐的服务
      * @return
      */
     public List<UserToServicesVo>getUserToServicesVo();
     /**
      * 新增站内信
      * @param news
      * @return
      */
     public int addNews(@Param("news")News news);
     /**
      * 添加积分流水
      * @param integralRecord
      * @return
      */
     public int addIntegralRecord(@Param("integralRecord")Integralrecord integralRecord);
}