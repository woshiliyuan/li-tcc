package com.li.tcc.admin.helper;

import com.li.tcc.admin.page.PageParameter;

/**
 * ConvertHelper
 * 
 * @author yuan.li
 * @version 1.0
 */
public class PageHelper {

	/**
	 * build PageParameter
	 * 
	 * @param pageParameter
	 * @param totalCount
	 * @return
	 */
	public static PageParameter buildPage(final PageParameter pageParameter, final int totalCount) {
		final int currentPage = pageParameter.getCurrentPage();
		pageParameter.setTotalCount(totalCount);
		int totalPage = totalCount / pageParameter.getPageSize()
				+ ((totalCount % pageParameter.getPageSize() == 0) ? 0 : 1);
		pageParameter.setTotalPage(totalPage);
		pageParameter.setPrePage(currentPage - 1);
		pageParameter.setNextPage(currentPage + 1);
		return pageParameter;
	}

	/**
	 * mysql build page sql
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	public static StringBuilder buildPageSqlForMysql(final String sql, final PageParameter page) {
		StringBuilder pageSql = new StringBuilder(100);
		String start = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());
		pageSql.append(sql);
		pageSql.append(" limit ").append(start).append(",").append(page.getPageSize());
		return pageSql;
	}

	/**
	 * oracle page sql
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	public static StringBuilder buildPageSqlForOracle(final String sql, final PageParameter page) {
		StringBuilder pageSql = new StringBuilder(100);
		String start = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());
		String end = String.valueOf(page.getCurrentPage() * page.getPageSize());
		pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
		pageSql.append(sql).append(" ) temp where rownum <= ").append(end);
		pageSql.append(" ) where row_id > ").append(start);
		return pageSql;
	}

	public static StringBuilder buildPageSqlForPostgreSQL(final String sql, final PageParameter page) {
		StringBuilder pageSql = new StringBuilder(100);
		String start = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());
		pageSql.append(sql);
		pageSql.append(" limit ").append(page.getPageSize()).append(" offset ").append(start);
		return pageSql;
	}

}
