package com.accp.action.lwx;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accp.biz.lwx.MyFavoriteBiz;
import com.accp.dao.lwx.MyFavoriteDao;
import com.accp.pojo.Goldnotes;
import com.accp.pojo.Integralrecord;
import com.accp.pojo.Services;
import com.accp.pojo.User;
import com.accp.vo.lwx.myfavorite.UserToServicesVo;
import com.accp.vo.lwx.myfavorite.UserVo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/c/lwx")
public class MyFavoriteAction {
	
	@Autowired
	private MyFavoriteBiz myFavoriteBiz;
	
	/**
	 * 查询收藏的服务
	 * @param model
	 * @param session
	 * @return 返回商家收藏页面 sc-sj。html
	 */
	@GetMapping("getServicesByUserId")
    public String getServicesByUserId(Model model, HttpSession session,Integer p, Integer s,Integer stid) {
		if (p == null)p = 1;
		if (s == null)s = 6;
		Integer userId=((User)session.getAttribute("USER")).getUserid();
		PageInfo<Services>pageInfo= myFavoriteBiz.getServicesByUserId(p, s, userId,stid);
		List<UserToServicesVo>list=myFavoriteBiz.getUserToServicesVo();
		model.addAttribute("LIST",list);
		model.addAttribute("PAGE_INFO",pageInfo);
		return "grzx-favs";
    }
	
	/**
	 * 查询收藏的服务及商家
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("getMerchantCollectionById")
	public String getMerchantCollectionById(Model model, HttpSession session,Integer p, Integer s,Integer stid) {
		if (p == null)p = 1;
		if (s == null)s = 6;
		Integer userId=((User)session.getAttribute("USER")).getUserid();
		PageInfo<UserVo>pageInfo= myFavoriteBiz.getMerchantCollectionById(p, s, userId,stid);
		List<UserToServicesVo>list=myFavoriteBiz.getUserToServicesVo();
		model.addAttribute("LIST",list);
		model.addAttribute("PAGE_INFO",pageInfo);
		return "sc-sj";
	}

	/**
	 * 查询金币流向记录表 （goldnotesQueryAll）
	 * 
	 * @param model
	 * @param session
	 * @param p
	 * @param s
	 * @return
	 */
	@GetMapping("goldnotesQueryAll")
	public String goldnotesQueryAll(Model model, HttpSession session, Integer p, Integer s,Integer acquisitionMode,String recordDate) {
		if (p == null)p = 1;
		if (s == null)s = 6;
		
		Integer userId=((User)session.getAttribute("USER")).getUserid();
		PageInfo<Goldnotes> pageInfo = myFavoriteBiz.goldnotesQueryAll(p, s, userId,acquisitionMode,recordDate);
		User users = myFavoriteBiz.getUser(userId);
		if(users.getUsermoney()==null) {
			users.setUsermoney((float) 0);
		}
		model.addAttribute("PAGE_INFO", pageInfo);
		model.addAttribute("USER", users);
		model.addAttribute("recordDate",recordDate);
		return "grzx-moneys";
	}
	
	/**
	 * 查询积分流向记录表（QueryAll）
	 * 
	 * @param model
	 * @param session
	 * @param p
	 * @param s
	 * @return
	 */
	@GetMapping("QueryAll")
	public String IntegralRecordQueryAll(Model model, HttpSession session, Integer p, Integer s) {
		if (p == null)p = 1;
		if (s == null)s = 6;
		Integer userId=((User)session.getAttribute("USER")).getUserid();
		User users=myFavoriteBiz.getUser(userId);
		PageInfo<Integralrecord> pageInfo = myFavoriteBiz.IntegralRecordQueryAll(p, s, userId);
		model.addAttribute("PAGE_INFO", pageInfo);
		model.addAttribute("USER",users);
		return "grzx-points";
	}
}
