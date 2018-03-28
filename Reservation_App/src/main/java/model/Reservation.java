package model;

public class Reservation {

	private int id;
	
	private double price;
	
	private int seatId;
	
	private int eventId;
	
	private int userId;

	public Reservation(double Price, int SeatId, int EventId, int UserId) {
		super();
		price = Price;
		seatId = SeatId;
		eventId = EventId;
		userId = UserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	
}
