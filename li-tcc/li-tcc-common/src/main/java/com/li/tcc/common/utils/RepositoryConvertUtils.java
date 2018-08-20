package com.li.tcc.common.utils;

import org.apache.commons.collections.CollectionUtils;

import com.li.tcc.common.bean.adapter.CoordinatorRepositoryAdapter;
import com.li.tcc.common.bean.entity.Participant;
import com.li.tcc.common.bean.entity.TccTransaction;
import com.li.tcc.common.exception.TccException;
import com.li.tcc.common.serializer.ObjectSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * RepositoryConvertUtils
 * 
 * @author yuan.li
 */
public class RepositoryConvertUtils {

	public static byte[] convert(final TccTransaction tccTransaction, final ObjectSerializer objectSerializer)
			throws TccException {
		CoordinatorRepositoryAdapter adapter = new CoordinatorRepositoryAdapter();
		adapter.setTransId(tccTransaction.getTransId());
		adapter.setLastTime(tccTransaction.getLastTime());
		adapter.setCreateTime(tccTransaction.getCreateTime());
		adapter.setRetriedCount(tccTransaction.getRetriedCount());
		adapter.setStatus(tccTransaction.getStatus());
		adapter.setTargetClass(tccTransaction.getTargetClass());
		adapter.setTargetMethod(tccTransaction.getTargetMethod());
		adapter.setPattern(tccTransaction.getPattern());
		adapter.setRole(tccTransaction.getRole());
		adapter.setVersion(tccTransaction.getVersion());
		if (CollectionUtils.isNotEmpty(tccTransaction.getParticipants())) {
			final Participant participant = tccTransaction.getParticipants().get(0);
			adapter.setConfirmMethod(participant.getConfirmTccInvocation().getMethodName());
			adapter.setCancelMethod(participant.getCancelTccInvocation().getMethodName());
		}
		adapter.setContents(objectSerializer.serialize(tccTransaction.getParticipants()));
		return objectSerializer.serialize(adapter);
	}

	@SuppressWarnings("unchecked")
	public static TccTransaction transformBean(final byte[] contents, final ObjectSerializer objectSerializer)
			throws TccException {
		TccTransaction tccTransaction = new TccTransaction();
		final CoordinatorRepositoryAdapter adapter = objectSerializer.deSerialize(contents,
				CoordinatorRepositoryAdapter.class);
		List<Participant> participants = objectSerializer.deSerialize(adapter.getContents(), ArrayList.class);
		tccTransaction.setLastTime(adapter.getLastTime());
		tccTransaction.setRetriedCount(adapter.getRetriedCount());
		tccTransaction.setCreateTime(adapter.getCreateTime());
		tccTransaction.setTransId(adapter.getTransId());
		tccTransaction.setStatus(adapter.getStatus());
		tccTransaction.setParticipants(participants);
		tccTransaction.setRole(adapter.getRole());
		tccTransaction.setPattern(adapter.getPattern());
		tccTransaction.setTargetClass(adapter.getTargetClass());
		tccTransaction.setTargetMethod(adapter.getTargetMethod());
		tccTransaction.setVersion(adapter.getVersion());
		return tccTransaction;
	}

}
