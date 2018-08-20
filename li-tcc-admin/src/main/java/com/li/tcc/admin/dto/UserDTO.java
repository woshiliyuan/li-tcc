package com.li.tcc.admin.dto;

import java.io.Serializable;

/**
 * UserDTO
 * 
 * @author yuan.li
 */
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -3479973014221253748L;

	private String userName;

	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
