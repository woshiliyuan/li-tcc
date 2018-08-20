package com.li.tcc.core.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import com.li.tcc.common.bean.entity.TccTransaction;
import com.li.tcc.core.coordinator.CoordinatorService;
import com.li.tcc.core.helper.SpringBeanUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * use google guava cache
 * 
 * @author yuan.li
 */
public final class TccTransactionCacheManager {

	private static final int MAX_COUNT = 10000;

	private static final LoadingCache<String, TccTransaction> LOADING_CACHE = CacheBuilder.newBuilder()
			.maximumWeight(MAX_COUNT).weigher((Weigher<String, TccTransaction>) (string, tccTransaction) -> getSize())
			.build(new CacheLoader<String, TccTransaction>() {
				@Override
				public TccTransaction load(final String key) {
					return cacheTccTransaction(key);
				}
			});

	private static CoordinatorService coordinatorService = SpringBeanUtils.getInstance().getBean(
			CoordinatorService.class);

	private static final TccTransactionCacheManager TCC_TRANSACTION_CACHE_MANAGER = new TccTransactionCacheManager();

	private TccTransactionCacheManager() {

	}

	/**
	 * @return
	 */
	public static TccTransactionCacheManager getInstance() {
		return TCC_TRANSACTION_CACHE_MANAGER;
	}

	private static int getSize() {
		return (int) LOADING_CACHE.size();
	}

	private static TccTransaction cacheTccTransaction(final String key) {
		return Optional.ofNullable(coordinatorService.findByTransId(key)).orElse(new TccTransaction());
	}

	/**
	 * cache tccTransaction
	 * 
	 * @param tccTransaction
	 */
	public void cacheTccTransaction(final TccTransaction tccTransaction) {
		LOADING_CACHE.put(tccTransaction.getTransId(), tccTransaction);
	}

	/**
	 * get TccTransaction
	 * 
	 * @param key
	 *            this guava key
	 * @return
	 */
	public TccTransaction getTccTransaction(final String key) {
		try {
			return LOADING_CACHE.get(key);
		} catch (ExecutionException e) {
			return new TccTransaction();
		}
	}

	/**
	 * remove guava cache by key
	 * 
	 * @param key
	 *            guava cache key
	 */
	public void removeByKey(final String key) {
		if (StringUtils.isNotEmpty(key)) {
			LOADING_CACHE.invalidate(key);
		}
	}

}
