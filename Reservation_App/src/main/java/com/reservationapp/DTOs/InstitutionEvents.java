package com.reservationapp.DTOs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.reservationapp.model.Event;

public class InstitutionEvents {
	
	private List<List<Event>> groups;
	
	public InstitutionEvents(){
		
	}
	
	public InstitutionEvents(List<Event> events){
		groups = new ArrayList<List<Event>>();
		List<Event> removed = new ArrayList<Event>();
		for(Event e : events){
			List<Event> tempEvents = new ArrayList<Event>();
			int counter = 0;
			for(Event temp : events){
				if(temp != e && !removed.contains(temp)){
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					cal1.setTime(e.getEventDate());
					cal2.setTime(temp.getEventDate());
					boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
					                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
					
					if(temp.getShow() == e.getShow() && sameDay ){
						if(counter == 0){
							tempEvents.add(e);
							removed.add(e);
							counter++;
						}
						tempEvents.add(temp);
						removed.add(temp);
					}
					
				}
			}
			if(tempEvents.size() == 0 && !removed.contains(e)){
				tempEvents.add(e);
			}
			if(tempEvents.size() > 0){
				groups.add(tempEvents);
			}
			
		}
	}
	
	public List<List<Event>> getGroups() {
		return groups;
	}

	public void setGroups(List<List<Event>> groups) {
		this.groups = groups;
	}
}
