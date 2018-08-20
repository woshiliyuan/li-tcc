package com.li.tcc.core.coordinator.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.li.tcc.common.bean.entity.TccTransaction;
import com.li.tcc.common.config.TccConfig;
import com.li.tcc.core.coordinator.CoordinatorService;
import com.li.tcc.core.helper.SpringBeanUtils;
import com.li.tcc.core.schedule.ScheduledService;
import com.li.tcc.core.service.RpcApplicationService;
import com.li.tcc.core.spi.CoordinatorRepository;

/**
 * CoordinatorServiceImpl
 * 
 * @author yuan.li
 */
@Service("coordinatorService")
public class CoordinatorServiceImpl implements CoordinatorService {

	private CoordinatorRepository coordinatorRepository;

	private final RpcApplicationService rpcApplicationService;

	@Autowired
	public CoordinatorServiceImpl(final RpcApplicationService rpcApplicationService) {
		this.rpcApplicationService = rpcApplicationService;
	}

	@Override
	public void start(final TccConfig tccConfig) {
		final String repositorySuffix = buildRepositorySuffix(tccConfig.getRepositorySuffix());
		coordinatorRepository = SpringBeanUtils.getInstance().getBean(CoordinatorRepository.class);
		coordinatorRepository.init(repositorySuffix, tccConfig);
		new ScheduledService(tccConfig, coordinatorRepository).scheduledRollBack();
	}

	@Override
	public String save(final TccTransaction tccTransaction) {
		final int rows = coordinatorRepository.create(tccTransaction);
		if (rows > 0) {
			return tccTransaction.getTransId();
		}
		return null;
	}

	@Override
	public TccTransaction findByTransId(final String transId) {
		return coordinatorRepository.findById(transId);
	}

	@Override
	public boolean remove(final String id) {
		return coordinatorRepository.remove(id) > 0;
	}

	@Override
	public void update(final TccTransaction tccTransaction) {
		coordinatorRepository.update(tccTransaction);
	}

	@Override
	public int updateParticipant(final TccTransaction tccTransaction) {
		return coordinatorRepository.updateParticipant(tccTransaction);
	}

	@Override
	public int updateStatus(final String id, final Integer status) {
		return coordinatorRepository.updateStatus(id, status);
	}

	private String buildRepositorySuffix(final String repositorySuffix) {
		if (StringUtils.isNoneBlank(repositorySuffix)) {
			return repositorySuffix;
		} else {
			return rpcApplicationService.getName();
		}
	}
}
