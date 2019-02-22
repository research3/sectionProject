package com.accp.vo.lwx;


import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

public class ListVo {
	/**
	 * 存储安全码过期时间
	 */
	public static Map<String,TimeOutEmailDateVo> emailList=new HashMap<String,TimeOutEmailDateVo>();
	/**
	 * 存储加密私Key
	 */
	public static Map<String,RSAPrivateKey> emailService=new HashMap<String,RSAPrivateKey>();
}
