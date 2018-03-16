package model;

public class Seat {
	
	private int Id;
	
	private int Row;
	
	private int SeatNumber;
	
	private int SeatTypeId;
	
	
	public Seat(int row, int seatNumber, int seatTypeId) {
		super();
		Row = row;
		SeatNumber = seatNumber;
		SeatTypeId = seatTypeId;
	}

	public int getId() {
		return Id;
	}

	public int getRow() {
		return Row;
	}

	public void setRow(int row) {
		Row = row;
	}

	public int getSeatNumber() {
		return SeatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		SeatNumber = seatNumber;
	}

	public int getSeatTypeId() {
		return SeatTypeId;
	}

	public void setSeatTypeId(int seatTypeId) {
		SeatTypeId = seatTypeId;
	}
	
	
}
