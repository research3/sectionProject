package com.accp.biz.lwx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lwx.UserDao;
import com.accp.pojo.News;
import com.accp.pojo.Sharea;
import com.accp.pojo.User;
import com.accp.vo.lwx.NewsVo;
import com.accp.vo.lwx.TimeOutEmailDateVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class UserBiz {
	
	@Autowired
	private UserDao dao;
	
	public boolean queryEmail(String email) {
		/**
		 * 查询的数字返回0代表可新增
		 */
		return dao.queryEmail(email)==0;
	}
	
	/**
	 * 邮箱用户注册
	 * @param emailDate
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean saveEmailUser(TimeOutEmailDateVo emailDate) {
		return dao.saveEmailUser(emailDate)>=0;
	}
	
	/**
	 * 邮箱登陆
	 * @param emailDate
	 * @return
	 */
	public User login(String email,String password) {
		return dao.login(email, password);
	}
	
	/**
	 * 查询地址
	 * @param pid
	 * @param flag
	 * @return
	 */
	public List<Sharea> querySharea(){
		return dao.querySharea();
	}
	/**
	 * 修改个人信息
	 * @param u
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean updateUserInfo(User u) {
		return dao.updateUserInfo(u)>0;
	}
	
	/**
	 * 修改密码
	 * @param email
	 * @param password
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean updatePwd(String email,String password){
		return dao.updatePwd(email, password)>0;
	}
	
	/**
	 * 查询当前用户
	 * @param userID
	 * @return
	 */
	public User queryUser(Integer userID){
		return dao.queryUser(userID);
	}
	
	/**
	 * 根据编号获取用户
	 * 
	 * @param userid
	 *            用户编号
	 * @return
	 */
	public User queryUserById(int userid) {
		return dao.queryUserById(userid);
	}
	
	/**
	 * 修改用户头像
	 * @param userImgPath
	 * @param userID
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean updateUserImg(String userImgPath,Integer  userID) {
		return dao.updateUserImg(userImgPath, userID)>0;
	}
	/**
	 * 查询服务类别
	 * @param id
	 * @return
	 */
	public String querySerType(Integer id) {
		return dao.querySerType(id);
	}
	/**
	 * 修改店铺信息
	 * @param u
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean updateUserDpxx(User u) {
		return dao.updateUserDpxx(u)>0;
	}
	/**
	 * 系统分页查询
	 * @param userID
	 * @param newsType
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<News>  queryNewPageInfo(Integer userID,Integer newsType,Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(dao.queryXtNews(userID, newsType));
	}
	/**
	 * 修改已读
	 * @param newsID
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public int updateXtNews(String newsID) {
		return dao.updateXtNews(newsID);
	}
	/**
	 * 删除系统信息
	 * @param newsID
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public int deleteNews(String newsID) {
		return dao.deleteNews(newsID);
	}
	
	/**
	 * 站内信分页查询
	 * @param userID
	 * @param newsType
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<NewsVo>  queryZnxNewsPageInfo(Integer userID,Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(dao.queryZnxNews(userID));
	}
	/**
	 * 修改站内信信息
	 * @param groupID
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public int updateZnxNews(String groupID) {
		return dao.updateZnxNews(groupID);
	}
	/**
	 * 删除站内信
	 * @param newsID
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public int deleteZnxNews(String groupID) {
		return dao.deleteZnxNews(groupID);
	}
	/**
	 * 查询站内信详情
	 * @param userID
	 * @return
	 */
	public List<NewsVo> queryZnxXq(String groupID){
		return dao.queryZnxXq(groupID);
	}
	/**
	 * 新增站内信
	 * @param news
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean saveZnx(News news) {
		return dao.saveZnx(news)>0;
	}
	/**
	 * 查询所有信息
	 * @param userID
	 * @return
	 */
	public List<News> queryAllNews(Integer userID){
		return dao.queryAllNews(userID);
	}
	/**
	 * 新增系统消息
	 * @param userID 用户ID
	 * @param content 内容
	 * @return
	 */
	public boolean saveXtxx(Integer userID,String content) {
		return dao.saveXtxx(userID, content)>0;
	}
}
