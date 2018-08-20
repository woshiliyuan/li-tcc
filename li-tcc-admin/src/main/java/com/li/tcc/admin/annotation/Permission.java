package com.li.tcc.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Permission
 * 
 * @author yuan.li
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {

	/**
	 * is login
	 *
	 * @return true
	 */
	boolean isLogin() default true;

}
