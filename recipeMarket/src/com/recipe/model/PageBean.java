package com.recipe.model;

import java.util.List;

public class PageBean {
	public static final int CNT_PER_PAGE = 10; // 페이지당 보여줄 목록수, service 단에 있었다.
	public static final int CNT_PER_PAGEGROUP = 5; // 페이지 그룹 수

	private String url = ""; // 링크 클릭시 이동할 URL
	private int currentPage; // 현재 페이지
	private int startRow; // 페이지당 보여줄 시작행
	private int endRow; // 페이지당 보여줄 끝행
	private int totalPage; // 총 페이지 수
	private List list;
	private int startPage; // 페이지 그룹의 시작 페이지
	private int endPage; // 페이지 그룹의 끝 페이지
	private int rowCnt; // 총 행 갯수

	public PageBean() { }

	public PageBean(int currentPage) {
		this.currentPage = currentPage;
		this.endRow = CNT_PER_PAGE * currentPage;
		this.startRow = endRow - (CNT_PER_PAGE - 1);
	}

	/**
	 * 
	 * @param currentPage
	 * @param rowCnt
	 * @author yonghwan
	 */
	public PageBean(int currentPage, int rowCnt) {
		this(currentPage);
				
		int totalPage = (rowCnt + PageBean.CNT_PER_PAGE - 1) / PageBean.CNT_PER_PAGE;
		setTotalPage(totalPage);

		int endPage = ((currentPage - 1) / PageBean.CNT_PER_PAGEGROUP + 1) * PageBean.CNT_PER_PAGEGROUP;
		int startPage = endPage - (PageBean.CNT_PER_PAGEGROUP - 1);

		endPage = Math.min(endPage, totalPage);

		setStartPage(startPage);
		setEndPage(endPage);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
	public int getRowCnt() {
		return rowCnt;
	}

	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}

	@Override
	public String toString() {
		return "PageBean [url=" + url + ", currentPage=" + currentPage + ", startRow=" + startRow + ", endRow=" + endRow
				+ ", totalPage=" + totalPage + ", list=" + list + ", startPage=" + startPage + ", endPage=" + endPage
				+ "]";
	}
}
