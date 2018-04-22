package test.constants;

import java.util.ArrayList;
import java.util.List;

import com.reservationapp.model.Hall;
import com.reservationapp.model.Seat;
import com.reservationapp.model.SeatType;

public class SeatConstants {

	public static final Long NEW_ID = 1L;
	public static final int NEW_ROW = 1;
	public static final int NEW_SEAT_NUMBER = 1;
	public static final Hall NEW_HALL = new Hall();
	public static final SeatType NEW_SEAT_TYPE = new SeatType();
	
	
	public static final Long DB_ID = 1L;
	public static final int DB_ROW = 1;
	public static final int DB_SEAT_NUMBER = 1;
	public static final Hall DB_HALL = new Hall();
	public static final SeatType DB_SEAT_TYPE = new SeatType();
	public static final List<List<Seat>> DB_SEATS = new ArrayList<List<Seat>>();
	public static final List<Hall> DB_HALLS = new ArrayList<Hall>();
}
