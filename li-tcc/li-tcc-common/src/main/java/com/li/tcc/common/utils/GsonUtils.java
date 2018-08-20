package com.li.tcc.common.utils;

import com.google.gson.Gson;

/**
 * GsonUtils
 * 
 * @author yuan.li
 */
public class GsonUtils {

	private static final GsonUtils GSON_UTILS = new GsonUtils();

	private static final Gson GSON = new Gson();

	public static GsonUtils getInstance() {
		return GSON_UTILS;
	}

	public String toJson(final Object object) {
		return GSON.toJson(object);
	}

	public <T> T fromJson(final String json, final Class<T> tClass) {
		return GSON.fromJson(json, tClass);
	}
}
