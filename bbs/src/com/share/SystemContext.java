package com.share;


public class SystemContext {
	
	private static ThreadLocal<Integer> page = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pagesize = new ThreadLocal<Integer>();

	public static Integer getPage() {
		return page.get();
	}

	public static void setPage(Integer _page) {
		page.set(_page);
	}

	public static Integer getPageSize() {
		return pagesize.get();
	}

	public static void setPagesize(Integer _pagesize) {
		pagesize.set(_pagesize);
	}

	public static void removePage() {
		page.remove();
	}

	public static void removePagesize() {
		pagesize.remove();
	}
}
