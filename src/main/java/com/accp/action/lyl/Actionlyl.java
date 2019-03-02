package com.accp.action.lyl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accp.biz.lyl.Lylbiz;
import com.accp.pojo.Servicelevel;
import com.accp.pojo.Services;
import com.accp.pojo.Servicetype;
import com.accp.pojo.Sharea;
import com.accp.vo.lyl.ErjixxVo;
import com.accp.vo.lyl.ErjiyemcxVo;
import com.github.pagehelper.PageInfo;

/**   
* 项目名称：HanTingProject   
* 类名称：Actionlyl   
* 类描述：   
* 创建人：刘应龙 
* 创建时间：2019年2月20日
* @version        
*/
@RestController
@RequestMapping("/c/lyl")
public class Actionlyl {
	@Autowired
	private Lylbiz biz;

	//二级页面查询
	@RequestMapping(value = "/queryServices/{pageNum}", method = RequestMethod.POST)
	public PageInfo<ErjixxVo> queryServices(@PathVariable int pageNum,@RequestBody ErjiyemcxVo _vo) {
		System.out.println(_vo.toString()+"113213213");
		return biz.erjquery(pageNum,6,_vo);
	}
	//二级页面查询国家城市
	@RequestMapping(value = "/queryCountry/{pid}", method = RequestMethod.GET)
	public List<Sharea> queryCountry(@PathVariable int pid) {
		return biz.queryCountry(pid);
	}
	//二级页面查询类别
	@RequestMapping(value = "/queryserType/{stPid}", method = RequestMethod.GET)
	public List<Servicetype> queryserType(@PathVariable int stPid) {
		return biz.queryserType(stPid);
	}
	//二级页面查询类别
	@RequestMapping(value = "/queryserLeve/{stid}", method = RequestMethod.GET)
	public List<Servicelevel> queryserLeve(@PathVariable int stid) {
		return biz.queryserLeve(stid);
	}
	
	//查看个人喜好
	@RequestMapping(value = "/queryllike/{userid}/{stid}", method = RequestMethod.GET)
	public ErjiyemcxVo queryserLeve(@PathVariable int userid,@PathVariable int stid) {
		System.out.println(biz.queryzhin(userid, stid).toString());
		return biz.queryzhin(userid, stid);
	}
	
}
