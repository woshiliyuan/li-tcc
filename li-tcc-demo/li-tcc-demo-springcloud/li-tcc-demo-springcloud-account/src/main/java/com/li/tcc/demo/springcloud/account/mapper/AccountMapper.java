package com.li.tcc.demo.springcloud.account.mapper;

import com.li.tcc.demo.springcloud.account.entity.AccountDO;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author yuan.li
 */
public interface AccountMapper {

	/**
	 * 扣减账户余额
	 *
	 * @param accountDO
	 *            实体类
	 * @return rows
	 */
	@Update("update account set balance =#{balance}," + " freeze_amount= #{freezeAmount} ,update_time = #{updateTime}"
			+ " where user_id =#{userId}  and  balance > 0 ")
	int update(AccountDO accountDO);

	/**
	 * 确认扣减账户余额
	 *
	 * @param accountDO
	 *            实体类
	 * @return rows
	 */
	@Update("update account set " + " freeze_amount= #{freezeAmount} ,update_time = #{updateTime}"
			+ " where user_id =#{userId}  and freeze_amount >0 ")
	int confirm(AccountDO accountDO);

	/**
	 * 取消扣减账户余额
	 *
	 * @param accountDO
	 *            实体类
	 * @return rows
	 */
	@Update("update account set balance =#{balance}," + " freeze_amount= #{freezeAmount} ,update_time = #{updateTime}"
			+ " where user_id =#{userId}  and freeze_amount >0")
	int cancel(AccountDO accountDO);

	/**
	 * 根据userId获取用户账户信息
	 *
	 * @param userId
	 *            用户id
	 * @return AccountDO
	 */
	@Select("select * from account where user_id =#{userId}")
	AccountDO findByUserId(String userId);
}
