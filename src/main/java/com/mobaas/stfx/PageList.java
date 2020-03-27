/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx;

import java.util.List;

public class PageList<T> {

	private int pageNo;
	private int pageSize;
	private int total;
	private List<T> list;
	
	public PageList(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public int getOffset() {
		return pageNo > 0 ? (pageNo-1) * pageSize : 0; 
	}
}
