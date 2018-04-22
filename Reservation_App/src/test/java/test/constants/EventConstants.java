package test.constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Reservation;
import com.reservationapp.model.Seat;
import com.reservationapp.model.Show;

public class EventConstants {

	public static final Date NEW_EVENTDATE = new Date();
	public static final Hall NEW_HALL = new Hall();
	public static final int NEW_PRICE = 100;
	public static final Show NEW_SHOW = new Show();
	
	public static final List<List<Event>> DB_GROUPS = new ArrayList<List<Event>>();
	public static final List<Hall> DB_HALLS = new ArrayList<Hall>();
	public static final List<Reservation> DB_RESERVATIONS = new ArrayList<Reservation>();
	public static final List<Seat> DB_SEATS = new ArrayList<Seat>();
	public static final List<Show> DB_SHOWS = new ArrayList<Show>();
	public static final Event DB_EVENT = new Event();
	public static final Long DB_ID = 1L;
	public static final Date DB_EVENTDATE = new Date();
	public static final Hall DB_HALL = new Hall();
	public static final int DB_PRICE = 100;
	public static final Show DB_SHOW = new Show();
}
