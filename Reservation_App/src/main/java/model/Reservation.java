package model;

public class Reservation {

	private int Id;
	
	private double Price;
	
	private int SeatId;
	
	private int EventId;
	
	private int UserId;

	public Reservation(double price, int seatId, int eventId, int userId) {
		super();
		Price = price;
		SeatId = seatId;
		EventId = eventId;
		UserId = userId;
	}

	public int getId() {
		return Id;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public int getSeatId() {
		return SeatId;
	}

	public void setSeatId(int seatId) {
		SeatId = seatId;
	}

	public int getEventId() {
		return EventId;
	}

	public void setEventId(int eventId) {
		EventId = eventId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}
	
	
	
}
