package com.li.tcc.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.li.tcc.common.config.TccConfig;
import com.li.tcc.common.enums.RepositorySupportEnum;
import com.li.tcc.common.enums.SerializeEnum;
import com.li.tcc.common.serializer.KryoSerializer;
import com.li.tcc.common.serializer.ObjectSerializer;
import com.li.tcc.common.utils.LogUtil;
import com.li.tcc.common.utils.ServiceBootstrap;
import com.li.tcc.core.coordinator.CoordinatorService;
import com.li.tcc.core.disruptor.publisher.LiTransactionEventPublisher;
import com.li.tcc.core.helper.SpringBeanUtils;
import com.li.tcc.core.service.LiInitService;
import com.li.tcc.core.spi.CoordinatorRepository;
import com.li.tcc.core.spi.repository.JdbcCoordinatorRepository;

import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * li tcc init service
 * 
 * @author yuan.li
 */
@Service("tccInitService")
public class LiInitServiceImpl implements LiInitService {

	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LiInitServiceImpl.class);

	private final CoordinatorService coordinatorService;

	private final LiTransactionEventPublisher liTransactionEventPublisher;

	@Autowired
	public LiInitServiceImpl(final CoordinatorService coordinatorService,
			final LiTransactionEventPublisher liTransactionEventPublisher) {
		this.coordinatorService = coordinatorService;
		this.liTransactionEventPublisher = liTransactionEventPublisher;
	}

	/**
	 * li initialization
	 *
	 * @param tccConfig
	 *            TccConfig
	 */
	@Override
	public void initialization(final TccConfig tccConfig) {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> LOGGER.info("li shutdown now")));
		try {
			loadSpiSupport(tccConfig);
			liTransactionEventPublisher.start(tccConfig.getBufferSize());
			coordinatorService.start(tccConfig);
		} catch (Exception ex) {
			LogUtil.error(LOGGER, " li init exception:{}", ex::getMessage);
			// 非正常关闭
			System.exit(1);
		}
		LogUtil.info(LOGGER, () -> "li init success!");
	}

	/**
	 * load spi
	 *
	 * @param tccConfig
	 *            TccConfig
	 */
	private void loadSpiSupport(final TccConfig tccConfig) {
		// spi serialize
		final SerializeEnum serializeEnum = SerializeEnum.getEnum(tccConfig.getSerializer());
		final ServiceLoader<ObjectSerializer> objectSerializers = ServiceBootstrap.loadAll(ObjectSerializer.class);
		final ObjectSerializer serializer = StreamSupport.stream(objectSerializers.spliterator(), false)
				.filter(objectSerializer -> Objects.equals(objectSerializer.getScheme(), serializeEnum.getSerialize()))
				.findFirst().orElse(new KryoSerializer());
		// spi repository
		final RepositorySupportEnum repositorySupportEnum = RepositorySupportEnum.getEnum(tccConfig
				.getRepositorySupport());
		final ServiceLoader<CoordinatorRepository> recoverRepositories = ServiceBootstrap
				.loadAll(CoordinatorRepository.class);
		final CoordinatorRepository repository = StreamSupport
				.stream(recoverRepositories.spliterator(), false)
				.filter(recoverRepository -> Objects.equals(recoverRepository.getScheme(),
						repositorySupportEnum.getSupport())).findFirst().orElse(new JdbcCoordinatorRepository());
		repository.setSerializer(serializer);
		// 将CoordinatorRepository实现注入到spring容器
		SpringBeanUtils.getInstance().registerBean(CoordinatorRepository.class.getName(), repository);
	}
}
