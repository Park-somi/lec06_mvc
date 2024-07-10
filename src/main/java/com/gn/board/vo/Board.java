package com.gn.board.vo;

import java.time.LocalDateTime;

public class Board {
	private int board_no;
	private String board_title;
	private String board_content;
	private String board_writer;
	private LocalDateTime reg_date;
	private LocalDateTime mod_date;
	private String ori_thumbnail;
	private String new_thumbnail;
	
	public Board() {
		super();
	}

	public Board(int board_no, String board_title, String board_content, String board_writer, LocalDateTime reg_date,
			LocalDateTime mod_date, String ori_thumbnail, String new_thumbnail) {
		super();
		this.board_no = board_no;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_writer = board_writer;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
		this.ori_thumbnail = ori_thumbnail;
		this.new_thumbnail = new_thumbnail;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public LocalDateTime getReg_date() {
		return reg_date;
	}

	public void setReg_date(LocalDateTime reg_date) {
		this.reg_date = reg_date;
	}

	public LocalDateTime getMod_date() {
		return mod_date;
	}

	public void setMod_date(LocalDateTime mod_date) {
		this.mod_date = mod_date;
	}

	public String getOri_thumbnail() {
		return ori_thumbnail;
	}

	public void setOri_thumbnail(String ori_thumbnail) {
		this.ori_thumbnail = ori_thumbnail;
	}

	public String getNew_thumbnail() {
		return new_thumbnail;
	}

	public void setNew_thumbnail(String new_thumbnail) {
		this.new_thumbnail = new_thumbnail;
	}

	@Override
	public String toString() {
		return board_no + " || " + board_title + " || " + board_content
				+ " || " + board_writer + " || " + reg_date + " || " + mod_date
				+ " || " + ori_thumbnail + " || " + new_thumbnail;
	}
	
	
}
