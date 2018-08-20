package com.li.tcc.common.bean.entity;

import com.google.common.collect.Lists;
import com.li.tcc.common.utils.IdWorkerUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * TccTransaction 实体日志对象
 * 
 * @author yuan.li
 */
public class TccTransaction implements Serializable {

	private static final long serialVersionUID = -6792063780987394917L;

	/**
	 * 事务id
	 */
	private String transId;

	/**
	 * 事务状态 TccActionEnum
	 */
	private int status;

	/**
	 * 事务类型 TccRoleEnum
	 */
	private int role;

	/**
	 * 重试次数
	 */
	private volatile int retriedCount;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date lastTime;

	/**
	 * 版本号 乐观锁控制
	 */
	private Integer version = 1;

	/**
	 * 模式
	 */
	private Integer pattern;

	/**
	 * 调用接口名称
	 */
	private String targetClass;

	/**
	 * 调用方法名称
	 */
	private String targetMethod;

	/**
	 * 参与协调的方法集合
	 */
	private List<Participant> participants;

	public TccTransaction() {
		this.transId = IdWorkerUtils.getInstance().createUUID();
		this.createTime = new Date();
		this.lastTime = new Date();
		participants = Lists.newCopyOnWriteArrayList();

	}

	public TccTransaction(final String transId) {
		this.transId = transId;
		this.createTime = new Date();
		this.lastTime = new Date();
		participants = Lists.newCopyOnWriteArrayList();
	}

	/**
	 * 保存参与者
	 * 
	 * @param participant
	 *            参与者对象
	 */
	public void registerParticipant(final Participant participant) {
		participants.add(participant);
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getRetriedCount() {
		return retriedCount;
	}

	public void setRetriedCount(int retriedCount) {
		this.retriedCount = retriedCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getPattern() {
		return pattern;
	}

	public void setPattern(Integer pattern) {
		this.pattern = pattern;
	}

	public String getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}

	public String getTargetMethod() {
		return targetMethod;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
}
