package com.recipe.vo;

import java.util.Date;

public class Board {
	private int level;
	private int boardNo;
	private int parentNo;
	private String boardTitle;
	private String boardWriter;
	private Date boardDt;
	private String boardContent;
	private int boardViews;

	public Board() {}

	/**
	 * 
	 * @param level
	 * @param boardNo
	 * @param parentNo
	 * @param boardTitle
	 * @param boardWriter
	 * @param boardDt
	 * @param boardContent
	 * @param boardViews
	 */
	public Board(int level, int boardNo, int parentNo, String boardTitle, String boardWriter, Date boardDt,
			String boardContent, int boardViews) {
		super();
		this.level = level;
		this.boardNo = boardNo;
		this.parentNo = parentNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardDt = boardDt;
		this.boardContent = boardContent;
		this.boardViews = boardViews;
	}

	/**
	 * For writer
	 * 
	 * @param board_title
	 * @param board_writer
	 * @param board_content
	 */
	public Board(String boardTitle, String boardWriter, String boardContent) {
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
	}

	/**
	 * For replier
	 * 
	 * @param parent_no
	 * @param board_title
	 * @param board_writer
	 * @param board_content
	 */
	public Board(int parentNo, String boardTitle, String boardWriter, String boardContent) {
		this.parentNo = parentNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public Date getBoardDt() {
		return boardDt;
	}

	public void setBoardDt(Date boardDt) {
		this.boardDt = boardDt;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getBoardViews() {
		return boardViews;
	}

	public void setBoardViews(int boardViews) {
		this.boardViews = boardViews;
	}

	@Override
	public String toString() {
		return "Board [level=" + level + ", boardNo=" + boardNo + ", parentNo=" + parentNo + ", boardTitle="
				+ boardTitle + ", boardWriter=" + boardWriter + ", boardDt=" + boardDt + ", boardContent="
				+ boardContent + ", boardViews=" + boardViews + "]";
	}

}
