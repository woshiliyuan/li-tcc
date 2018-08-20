package com.li.tcc.core.service;

import java.util.List;

import com.li.tcc.common.bean.entity.Participant;

/**
 * @author yuan.li
 */
public interface LiRollbackService {

	/**
	 * 执行协调回滚方法
	 *
	 * @param participantList
	 *            需要协调的资源集合
	 */
	void execute(List<Participant> participantList);

}
