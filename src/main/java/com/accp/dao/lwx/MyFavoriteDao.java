package com.accp.dao.lwx;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.pojo.Goldnotes;
import com.accp.pojo.Integralrecord;
import com.accp.pojo.Services;
import com.accp.pojo.User;
import com.accp.vo.lwx.myfavorite.UserToServicesVo;
import com.accp.vo.lwx.myfavorite.UserVo;

public interface MyFavoriteDao {
	/**
     * 查询当前用户收藏的商家
     * @param userId
     * @return
     */
    public List<UserVo>getMerchantCollectionById(@Param("userId")Integer userId,@Param("stid") Integer stid);
    /**
     * 查询当前用户收藏的服务
     * @param userId
     * @return
     */
    public List<Services>getServicesByUserId(@Param("userId")Integer userId,@Param("stid") Integer stid);
    
    /**
     * 查询系统推荐的服务
     * @return
     */
    public List<UserToServicesVo>getUserToServicesVo();
    
    /**
     * 查询用户余额
     * @param userId
     * @return
     */
    public User getUser (@Param("userId")Integer userId);
    
    /**
	 * 根据 用户id查询金币流向表
	 * @param userId
	 * @return
	 */
    public List<Goldnotes>goldnotesQueryAll(@Param("userId")Integer userId,
    		@Param("acquisitionMode")Integer acquisitionMode,@Param("recordDate") String recordDate);
    
    /**
     * 查询积分流向
     * @param userId
     * @return
     */
    public List<Integralrecord>IntegralRecordQueryAll(@Param("userId")Integer userId);
}
