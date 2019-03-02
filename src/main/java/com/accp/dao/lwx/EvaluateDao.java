package com.accp.dao.lwx;

import org.apache.ibatis.annotations.Param;

import com.accp.vo.lwx.Evaluate.EvaluateVo;


public interface EvaluateDao {
	/**
	 * 保存评价
	 * 
	 * @param evaluate
	 *            评价
	 * @return
	 */
	boolean saveEvaluate(@Param("evaluate") EvaluateVo  evaluate);
}