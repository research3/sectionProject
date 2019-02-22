package com.accp.biz.lwx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lwx.IUserDao;
import com.accp.pojo.User;
import com.accp.vo.lwx.TimeOutEmailDateVo;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class UserBiz {
	
	@Autowired
	private IUserDao dao;
	
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
	 * 修改密码
	 * @param email
	 * @param password
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean updatePwd(String email,String password){
		return dao.updatePwd(email, password)>0;
	}
}
