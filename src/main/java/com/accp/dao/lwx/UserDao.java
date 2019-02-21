package com.accp.dao.lwx;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
}
