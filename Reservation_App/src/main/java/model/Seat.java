package model;

public class Seat {
	
	private int id;
	
	private int row;
	
	private int seatNumber;
	
	private int seatTypeId;
 
	public Seat(int id, int row, int seatNumber, int seatTypeId) {
		super();
		this.id = id;
		this.row = row;
		this.seatNumber = seatNumber;
		this.seatTypeId = seatTypeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getSeatTypeId() {
		return seatTypeId;
	}

	public void setSeatTypeId(int seatTypeId) {
		this.seatTypeId = seatTypeId;
	}
	
	

}
