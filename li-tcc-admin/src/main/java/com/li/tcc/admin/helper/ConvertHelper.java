package com.li.tcc.admin.helper;

import com.li.tcc.admin.vo.TccCompensationVO;

import com.li.tcc.common.bean.adapter.CoordinatorRepositoryAdapter;
import com.li.tcc.common.utils.DateUtils;

/**
 * ConvertHelper
 * 
 * @author yuan.li
 */
public class ConvertHelper {

	public static TccCompensationVO buildVO(final CoordinatorRepositoryAdapter adapter) {
		TccCompensationVO vo = new TccCompensationVO();
		vo.setTransId(adapter.getTransId());
		vo.setCreateTime(DateUtils.parseDate(adapter.getCreateTime()));
		vo.setRetriedCount(adapter.getRetriedCount());
		vo.setLastTime(DateUtils.parseDate(adapter.getLastTime()));
		vo.setVersion(adapter.getVersion());
		vo.setTargetClass(adapter.getTargetClass());
		vo.setTargetMethod(adapter.getTargetMethod());
		vo.setConfirmMethod(adapter.getConfirmMethod());
		vo.setCancelMethod(adapter.getCancelMethod());
		return vo;
	}

}
