package com.li.tcc.common.config;

/**
 * TccConfig tcc配置文件
 * 
 * @author yuan.li
 */
public class TccConfig {

	/**
	 * 资源后缀 此参数请填写 关于是事务存储路径
	 * 
	 * 1 如果是表存储 这个就是表名后缀，其他方式存储一样
	 * 
	 * 2如果此参数不填写，那么会默认获取应用的applicationName
	 */
	private String repositorySuffix;

	/**
	 * 提供不同的序列化对象 SerializeEnum
	 */
	private String serializer = "kryo";

	/**
	 * 任务调度线程大小
	 */
	private int scheduledThreadMax = Runtime.getRuntime().availableProcessors() << 1;

	/**
	 * 调度时间周期 单位秒
	 */
	private int scheduledDelay = 60;

	/**
	 * 最大重试次数
	 */
	private int retryMax = 3;

	/**
	 * 事务恢复间隔时间 单位秒（注意 此时间表示本地事务创建的时间多少秒以后才会执行）
	 */
	private int recoverDelayTime = 60;

	/**
	 * 补偿存储类型. RepositorySupportEnum
	 */
	private String repositorySupport = "db";

	/**
	 * disruptor bufferSize
	 */
	private int bufferSize = 1024;

	/**
	 * db配置
	 */
	private TccDbConfig tccDbConfig;

	/**
	 * mongo配置
	 */
	private TccMongoConfig tccMongoConfig;

	/**
	 * redis配置
	 */
	private TccRedisConfig tccRedisConfig;

	/**
	 * zookeeper配置
	 */
	private TccZookeeperConfig tccZookeeperConfig;

	/**
	 * file配置
	 */
	private TccFileConfig tccFileConfig;

	public String getRepositorySuffix() {
		return repositorySuffix;
	}

	public void setRepositorySuffix(String repositorySuffix) {
		this.repositorySuffix = repositorySuffix;
	}

	public String getSerializer() {
		return serializer;
	}

	public void setSerializer(String serializer) {
		this.serializer = serializer;
	}

	public int getScheduledThreadMax() {
		return scheduledThreadMax;
	}

	public void setScheduledThreadMax(int scheduledThreadMax) {
		this.scheduledThreadMax = scheduledThreadMax;
	}

	public int getScheduledDelay() {
		return scheduledDelay;
	}

	public void setScheduledDelay(int scheduledDelay) {
		this.scheduledDelay = scheduledDelay;
	}

	public int getRetryMax() {
		return retryMax;
	}

	public void setRetryMax(int retryMax) {
		this.retryMax = retryMax;
	}

	public int getRecoverDelayTime() {
		return recoverDelayTime;
	}

	public void setRecoverDelayTime(int recoverDelayTime) {
		this.recoverDelayTime = recoverDelayTime;
	}

	public String getRepositorySupport() {
		return repositorySupport;
	}

	public void setRepositorySupport(String repositorySupport) {
		this.repositorySupport = repositorySupport;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public TccDbConfig getTccDbConfig() {
		return tccDbConfig;
	}

	public void setTccDbConfig(TccDbConfig tccDbConfig) {
		this.tccDbConfig = tccDbConfig;
	}

	public TccMongoConfig getTccMongoConfig() {
		return tccMongoConfig;
	}

	public void setTccMongoConfig(TccMongoConfig tccMongoConfig) {
		this.tccMongoConfig = tccMongoConfig;
	}

	public TccRedisConfig getTccRedisConfig() {
		return tccRedisConfig;
	}

	public void setTccRedisConfig(TccRedisConfig tccRedisConfig) {
		this.tccRedisConfig = tccRedisConfig;
	}

	public TccZookeeperConfig getTccZookeeperConfig() {
		return tccZookeeperConfig;
	}

	public void setTccZookeeperConfig(TccZookeeperConfig tccZookeeperConfig) {
		this.tccZookeeperConfig = tccZookeeperConfig;
	}

	public TccFileConfig getTccFileConfig() {
		return tccFileConfig;
	}

	public void setTccFileConfig(TccFileConfig tccFileConfig) {
		this.tccFileConfig = tccFileConfig;
	}
}
