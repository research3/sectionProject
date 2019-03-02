/**
 * 
 */
package com.accp.action.lyl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accp.biz.lyl.Lylbiz;
import com.accp.pojo.Complainttype;
import com.accp.pojo.Servicelevel;
import com.accp.pojo.Servicetype;
import com.accp.pojo.Sharea;
import com.accp.vo.lyl.EsLevelVO;
import com.accp.vo.lyl.SameServiceVO;
import com.accp.vo.lyl.ServiceDetailInfo;
import com.accp.vo.lyl.ServiceMerchantInfo;

/**   
* 项目名称：HanTingProject   
* 类名称：Actiontiao   
* 类描述：   
* 创建人：刘应龙 
* 创建时间：2019年2月25日
* @version        
*/
@Controller
@RequestMapping("/c/lyl/tiao")
public class Actiontiao {
	@Autowired
	private Lylbiz biz;
	
	//跳服务自驾游页面
	@GetMapping("fwzjy")
	public String fwzjy(HttpSession session) {
		return "fw-zjyTAB";
	}
	/**
	 * 点击服务详情跳转对应详情MVC地址
	 * @param htmlUrl
	 * @param sid
	 * @param uid
	 * @return
	 */
	@GetMapping("serviceDetailUrl")
	public String serviceDetailUrl(Model model,String htmlUrl,Integer sid,Integer uid) {
		//查询发布服务的商家信息
		ServiceMerchantInfo serMerchantObj = biz.queryServiceMerchantInfo(uid,sid);
		//查询服务信息
		ServiceDetailInfo serDetailObj = biz.queryServiceDetailInfo(sid);
		serDetailObj.setSerDesList(biz.queryServiceDes(sid));
		//服务评价星级人数查询
		EsLevelVO esLObj = biz.queryEsLevelVO(sid);
		//同城服务查询
		List<SameServiceVO> sameserList = biz.querySameServiceVO(sid);
		//举报原因查询
		List<Complainttype> complainttypeList = biz.queryComplainttype();
		//更新浏览数
		biz.updateServiceBrowseNumber(sid);
		//广告查询：未完成
		model.addAttribute("serMerchantObj",serMerchantObj);
		model.addAttribute("serDetailObj",serDetailObj);
		model.addAttribute("esLObj",esLObj);
		model.addAttribute("sameserList",sameserList);
		model.addAttribute("complainttypeList",complainttypeList);
		return "fw-zjyXQ";
	}
}
