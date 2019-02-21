package com.accp.biz.lwx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lwx.MyFavoriteDao;
import com.accp.pojo.Goldnotes;
import com.accp.pojo.Integralrecord;
import com.accp.pojo.Services;
import com.accp.pojo.User;
import com.accp.vo.lwx.myfavorite.UserToServicesVo;
import com.accp.vo.lwx.myfavorite.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class MyFavoriteBiz {
	@Autowired
	private MyFavoriteDao myFavoriteDao;
	/**
	   * 查询收藏的服务
	   * @param pageNum
	   * @param pageSize
	   * @param userId
	   * @return
	   */
	  public PageInfo<Services>getServicesByUserId(Integer pageNum,Integer pageSize,Integer userId){
		  PageHelper.startPage(pageNum, pageSize);
		  return new PageInfo<Services>(myFavoriteDao.getServicesByUserId(userId));
	  }
	  /**
	   * 查询收藏的服务及商家
	   * @param pageNum
	   * @param pageSize
	   * @param userId
	   * @return
	   */
	  public PageInfo<UserVo>getMerchantCollectionById(Integer pageNum,Integer pageSize,Integer userId,Integer stid){
		  PageHelper.startPage(pageNum, pageSize);
		  return new PageInfo<UserVo>(myFavoriteDao.getMerchantCollectionById(userId,stid));
	  }
	  
	  public List<UserToServicesVo>getUserToServicesVo(){
		  return myFavoriteDao.getUserToServicesVo();
	  }
	  
		/**
		 * 金币流向表
		 * @param pageNum  
		 * @param pageSize
		 * @param userId用户id
		 * @return
		 */
	    public  PageInfo<Goldnotes> goldnotesQueryAll(Integer pageNum,Integer pageSize,Integer userId,Integer acquisitionMode) {
	    	 PageHelper.startPage(pageNum,pageSize);
	  	   return new PageInfo<Goldnotes>(myFavoriteDao.goldnotesQueryAll(userId,acquisitionMode));
	    }
	    
	    /**
	     * 查询用户余额
	     * @param userId
	     * @return
	     */
	    public User getUser(Integer userId) {
	  	  return myFavoriteDao.getUser(userId);
	    }
	    
	    /**
	     * 查询积分流向
	     * @param pageNum
	     * @param pageSize
	     * @param userId
	     * @return
	     */
	    public PageInfo<Integralrecord>IntegralRecordQueryAll(Integer pageNum,Integer pageSize,Integer userId){
	   	    PageHelper.startPage(pageNum,pageSize);
	    	return new PageInfo<Integralrecord>(myFavoriteDao.IntegralRecordQueryAll(userId));
	    }
}
