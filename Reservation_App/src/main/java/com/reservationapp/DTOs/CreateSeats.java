package com.reservationapp.DTOs;

public class CreateSeats {

	private int rows;
	
	private int cols;
	
	private Long hallId;
	
	private String typeName;
	
	public CreateSeats() {
		
	}

	public CreateSeats(int rows, int cols, Long hallId, String typeName) {
		super();
		this.rows = rows;
		this.cols = cols;
		this.hallId = hallId;
		this.typeName = typeName;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public Long getHallId() {
		return hallId;
	}

	public void setHallId(Long hallId) {
		this.hallId = hallId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
