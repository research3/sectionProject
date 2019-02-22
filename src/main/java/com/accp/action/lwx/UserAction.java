package com.accp.action.lwx;

import java.io.UnsupportedEncodingException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.biz.lwx.UserBiz;
import com.accp.pojo.User;
import com.accp.util.code.VerifyCode;
import com.accp.util.email.Email;
import com.accp.util.email.EmailBoard;
import com.accp.util.rsaKey.RSAUtils;
import com.accp.vo.lwx.ListVo;
import com.accp.vo.lwx.TimeOutEmailDateVo;

@Controller
@RequestMapping("/c/lwx")
public class UserAction {
	@Autowired
	private UserBiz biz;
	
	@RequestMapping(value="/user/login",method=RequestMethod.GET)
	public String login() {
		return "szy-login";
	}
	
	/**
	 * 验证账号
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/user/yzEmail",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> queryEmail(String email){
		Map<String,String> map=new HashMap<>();
		System.out.println("执行邮箱验证");
		try {
			if(biz.queryEmail(email)) {
				map.put("code", "200");
			}else {
				map.put("code", "500");
			}
		} catch (Exception e) {
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	/**
	 * 邮箱注册
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/user/gotoLogin",method=RequestMethod.POST)
	public String gotoLogin(Model model,String email,String username) {
		System.out.println(email+"-----"+username+"----");
		try {
			//生成安全码
			String codeId=VerifyCode.createVerifyCode(8);
			if(ListVo.emailList.get(email)==null) {
				ListVo.emailList.put(email,new TimeOutEmailDateVo(email, codeId, new Date()));
			}else {
				ListVo.emailList.get(email).setTime(new Date());
				ListVo.emailList.get(email).setCodeId(codeId);
			}
			
			System.out.println("执行Email新增=========");
			Email.sendSimpleMail(email, "用户注册", EmailBoard.register(username, "http://127.0.0.1:3001/c/lwx/user/emailYanz?email="+email+"&nickName="+username+"&codeId="+codeId));
			System.out.println("====================\n发送成功\n====================\n");
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
			System.out.println("====================\n发送失败\n====================\n");
		}
		return "redirect:/szy-login.html";
	}
	
	/**
	 * 激活邮箱
	 * @param model
	 * @param email
	 * @param nickName
	 * @param codeId
	 * @return
	 */
	@RequestMapping(value="/user/emailYanz",method=RequestMethod.GET)
	public String emailYanz(Model model,String email,String nickName,String codeId) {
		if(ListVo.emailList.get(email)==null) {
			return "/szy-yz-no.html";
		}else {
			if(ListVo.emailList.get(email).getCodeId().trim().equals(codeId.trim())) {
				model.addAttribute("user", new TimeOutEmailDateVo(email,nickName));
				return "/szy-zuce-yz.html";
			}else {
				return "/szy-yz-no.html";
			}
		}
	}
	
	/**
	 * 新增邮箱登陆用户
	 * @param tqedv
	 * @return
	 */
	@RequestMapping(value="/user/saveEmail",method=RequestMethod.POST)
	public String saveEmail(TimeOutEmailDateVo tqedv) {
		if(biz.saveEmailUser(tqedv)) {
			return "redirect:/szy-login.html";
		}else{
			return "/szy-zuce-yz.html";
		}
	}
	/**
	 * 获取公钥
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/user/rsaKey",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> generateRSAKey(String email){
				// 将公钥传到前端
	            Map<String,String> map = new HashMap<String,String>();
		 try {
				// 获取公钥和私钥
				HashMap<String, Object> keys = RSAUtils.getKeys();
	            RSAPublicKey publicKey = (RSAPublicKey) keys.get("public");
	            RSAPrivateKey privateKey = (RSAPrivateKey) keys.get("private");
	            // 保存私钥到 redis，也可以保存到数据库
	            try {
					ListVo.emailService.put(email, privateKey);
				} catch (Exception e) {
					System.out.println("私钥存储失败");
				}
	            // 注意返回modulus和exponent以16为基数的BigInteger的字符串表示形式
	            map.put("modulus", publicKey.getModulus().toString(16));
	            map.put("exponent", publicKey.getPublicExponent().toString(16));
	        } catch (Exception e) {
	        	map.put("msg", e.getMessage());
	        } 
		 return map;
	}
	
	/**
	 * 解密 登陆方法
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/user/checkRSAKey",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> checkRSAKey(HttpSession session,String email,String password) {
	        Object object = ListVo.emailService.get(email);
	        Map<String,String> map = new HashMap<String,String>();
	        try {
	            // 解密
	        	System.out.println(password);
	            String decryptByPrivateKey = RSAUtils.decryptByPrivateKey(password, (RSAPrivateKey) object);
	            System.out.println(decryptByPrivateKey);
	            User u=biz.login(email, decryptByPrivateKey);
	    		if(u!=null) {
	    			session.setAttribute("USER", u);
	    			session.setAttribute("Email", email);
	    			map.put("code", "200");
	    		}else {
	    			map.put("code", "500");
	    		}
	        } catch (Exception e) {
	            map.put("msg", e.getMessage());
	        }
	        return map;
	 }

	/**
	 * 站外修改密码
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/user/updatePwd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updatePwd(String email){
		Map<String,String> map=new HashMap<>();
		try {
			String pwd=VerifyCode.createVerifyCode(6);
			Email.sendSimpleMail(email, "重置密码", EmailBoard.verifyCode(email, "您的密码已重置,新密码为:", pwd));
			System.out.println("====================\n修改密码发送成功\n====================\n");
			if(biz.updatePwd(email, pwd)) {
				map.put("code", "200");
			}else {
				map.put("code", "500");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("====================\n修改密码发送失败\n====================\n");
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
