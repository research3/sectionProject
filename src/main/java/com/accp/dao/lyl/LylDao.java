/**
 * 
 */
package com.accp.dao.lyl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.pojo.Complainttype;
import com.accp.pojo.Love;
import com.accp.pojo.Servicedes;
import com.accp.pojo.Servicelevel;
import com.accp.pojo.Services;
import com.accp.pojo.Servicetype;
import com.accp.pojo.Sharea;
import com.accp.vo.lyl.ErjixxVo;
import com.accp.vo.lyl.ErjiyemcxVo;
import com.accp.vo.lyl.EsLevelVO;
import com.accp.vo.lyl.EvaluationserviceVO;
import com.accp.vo.lyl.SameServiceVO;
import com.accp.vo.lyl.ServiceDetailInfo;
import com.accp.vo.lyl.ServiceMerchantInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**   
* 项目名称：HanTingProject   
* 类名称：LylDao   
* 类描述：   
* 创建人：刘应龙 
* 创建时间：2019年2月20日
* @version        
*/

public interface LylDao {
	//二级页面查询
	public List<ErjixxVo> erjquery(@Param("vo")ErjiyemcxVo vo);

	//查询国家城市等信息
	public List<Sharea> queryCountry(@Param("pid")int pid);
	
	public List<Servicetype> queryserType(@Param("stPid")int stPid);
	
	public List<Servicelevel> queryserLeve(@Param("stid")int stid);
	
	public Love queryzhin(@Param("typeid")int typeid,@Param("userid")int userid,@Param("stid")int stid);
	
	public ServiceMerchantInfo queryServiceMerchantInfo(@Param("uid")Integer uid,@Param("sid")Integer sid);
		
	public List<Servicedes> queryServiceDes(@Param("sid")Integer sid);
	
	public List<EvaluationserviceVO> queryEvaluationserviceVO(@Param("sid")Integer sid);
	
	public ServiceDetailInfo queryServiceDetailInfo(@Param("sid")Integer sid);
	
	public EsLevelVO queryEsLevelVO(@Param("sid")Integer sid);
	
	public List<SameServiceVO> querySameServiceVO(@Param("sid")Integer sid);

	public List<Complainttype> queryComplainttype();
	
	public void updateServiceBrowseNumber(@Param("sid")Integer sid);
}
