package com.accp.dao.lwx;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.accp.pojo.News;
import com.accp.pojo.Sharea;
import com.accp.pojo.User;
import com.accp.vo.lwx.NewsVo;
import com.accp.vo.lwx.TimeOutEmailDateVo;
import com.accp.vo.lwx.UserVo;

@Component("lwxuserdao")
public interface UserDao {
	/**
	 * 根据编号获取用户
	 * 
	 * @param userid
	 *            编号
	 * @return
	 */
	UserVo queryUserById(int userid);

	/**
	 * 相对修改用余额
	 * 
	 * @param usermoney
	 *            相对余额
	 * @param userid
	 *            用户编号
	 * @return
	 */
	boolean updateUserMoney(@Param("usermoney") Float usermoney, @Param("userid") int userid);
	
	/**
	 * 验证邮箱是否存在
	 * @param email
	 * @return
	 */
	public Integer queryEmail(@Param("email")String email);
	
	/**
	 * 使用邮箱注册
	 * @return
	 */
	public int saveEmailUser(@Param("emailDate")TimeOutEmailDateVo emailDate);
	
	/**
	 * 使用邮箱登陆
	 * @param emailDate
	 * @return
	 */
	public User login(@Param("email")String email,@Param("password")String password);
	
	/**
	 * 修改邮件登陆密码
	 * @param email
	 * @param password
	 * @return
	 */
	public int updatePwd(@Param("email")String email,@Param("password")String password);
	
	/**
	 * 查看中韩行政地区
	 * @return
	 */
	public List<Sharea> querySharea();
	/**
	 * 修改个人资料
	 * @param u
	 * @return
	 */
	public int updateUserInfo(@Param("u")User u);
	
	/**
	 * 查询当前用户
	 * @param userID
	 * @return
	 */
	public User queryUser(@Param("userID")Integer  userID);
	
	/**
	 * 修改用户图片
	 * @param userImgPath
	 * @param userID
	 * @return
	 */
	public int updateUserImg(@Param("userImg")String userImgPath,@Param("userID")Integer  userID);
	/**
	 * 查询服务类别
	 * @param id
	 * @return
	 */
	public String querySerType(@Param("id")Integer id);
	/**
	 * 修改用户店铺信息
	 * @param u
	 * @return
	 */
	public int updateUserDpxx(@Param("u")User u);
	/**
	 * 查询用户系统信息
	 * @param userID
	 * @return
	 */
	public List<News> queryXtNews(@Param("userid")Integer userID,@Param("newsType")Integer newsType);
	/**
	 * 修改系统信息已读状态
	 * @param newsID
	 * @return
	 */
	public int updateXtNews(@Param("newsID")String newsID);
	/**
	 * 删除系统信息
	 * @param newsID
	 * @return
	 */
	public int deleteNews(@Param("newsID")String newsID);
	/**
	 * 查询站内信
	 * @param userID
	 * @return
	 */
	public List<NewsVo> queryZnxNews(@Param("userid")Integer userID);
	/**
	 * 修改站内信已读状态
	 * @param groupID
	 * @return
	 */
	public int updateZnxNews(@Param("groupID")String groupID);
	/**
	 * 删除站内信
	 * @param newsID
	 * @return
	 */
	public int deleteZnxNews(@Param("groupID")String groupID);
	/**
	 * 查询站内信详情
	 * @param groupID
	 * @return
	 */
	public List<NewsVo> queryZnxXq(@Param("groupID")String groupID);
	/**
	 * 新增站内信
	 * @param news
	 * @return
	 */
	public int saveZnx(@Param("news")News news);
	/**
	 * 查询所有信息
	 * @param userID
	 * @return
	 */
	public List<News> queryAllNews(@Param("userid")Integer userID);
	/**
	 * 新增系统信息
	 * @param userID
	 * @param content
	 * @return
	 */
	public int saveXtxx(@Param("userid")Integer userID,@Param("content")String content);

}
