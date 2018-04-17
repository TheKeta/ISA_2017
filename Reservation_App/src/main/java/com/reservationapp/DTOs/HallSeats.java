package com.reservationapp.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.reservationapp.model.Seat;

public class HallSeats {

	private List<List<Seat>> halls;

	public HallSeats(List<Seat> seats) {
		super();
		halls = new ArrayList<List<Seat>>();
		List<Seat> removed = new ArrayList<Seat>();
		for(Seat s : seats){
			List<Seat> tempList = new ArrayList<Seat>();
			for(Seat temp : seats){
				if(!removed.contains(temp) && s.getId()!=temp.getId()){
					if(s.getHall() == temp.getHall()){
						if(!removed.contains(s)){
							tempList.add(s);
							removed.add(s);
						}
						tempList.add(temp);
						removed.add(temp);
					}
				}
			}
			
			if(tempList.size() == 0 && !removed.contains(s)){
				tempList.add(s);
			}
			if(tempList.size() > 0){
				halls.add(tempList);
			}
		}
	}

	public List<List<Seat>> getHalls() {
		return halls;
	}

	public void setHalls(List<List<Seat>> halls) {
		this.halls = halls;
	}

	
	
	
}
