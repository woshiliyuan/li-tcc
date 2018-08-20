package com.li.tcc.admin.page;

import java.io.Serializable;
import java.util.List;

/**
 * common Pager
 * 
 * @author yuan.li
 */
public class CommonPager<T> implements Serializable {

	private static final long serialVersionUID = -1220101004792874251L;

	/**
	 * page
	 */
	private PageParameter page;

	/**
	 * data
	 */
	private List<T> dataList;

	public PageParameter getPage() {
		return page;
	}

	public void setPage(PageParameter page) {
		this.page = page;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
}
