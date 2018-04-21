package com.reservationapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.model.Friendship;
import com.reservationapp.model.User;
import com.reservationapp.model.UserForm;
import com.reservationapp.service.impl.FriendshipServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/friendship")
public class FriendshipController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private FriendshipServiceImpl friendshipService;

	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public String sendRequest(@RequestBody UserForm user){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedUser = userService.findOneByEmail(auth.getName());
		
		User reciever = userService.findOneByEmail(user.getEmail());
		
		if(loggedUser.getId() == reciever.getId())
			return "That's you!";
		
		List<Friendship> friendships = friendshipService.findAll();
		
		//da li vec postoji to prijateljstvo
		for(Friendship f : friendships){
			if((f.getSender().getId() == loggedUser.getId() && f.getReciever().getId() == reciever.getId())
					|| (f.getSender().getId() == reciever.getId() && f.getReciever().getId() == loggedUser.getId())){
				if(f.getAccepted() == 2)
					return "You are already friends";
				else if(f.getAccepted() == 1)
					return "Your request has been rejected";
				
				return "Request already sent!";
				}
		}
		Friendship friendship = new Friendship();
		friendship.setSender(loggedUser);
		friendship.setReciever(reciever);
		friendship.setAccepted(0);
		
		friendshipService.save(friendship);
		
		return "Request sent";
	}
	
	@RequestMapping(value="/requests", method = RequestMethod.GET)
	public ResponseEntity<List<UserForm>> getRequests(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedUser = userService.findOneByEmail(auth.getName());
		
		List<UserForm> res = new ArrayList<UserForm>();
		
		List<Friendship> requests = friendshipService.friedshipRequests();
		for(Friendship f : requests){
			if(f.getReciever().getId() == loggedUser.getId()){
				res.add(new UserForm(f.getSender()));
			}
		}
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/friends", method = RequestMethod.GET)
	public ResponseEntity<List<UserForm>> getFriends(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedUser = userService.findOneByEmail(auth.getName());
		
		List<UserForm> res = new ArrayList<UserForm>();
		
		List<Friendship> requests = friendshipService.acceptedFriendships();
		for(Friendship f : requests){
			if(f.getReciever().getId() == loggedUser.getId()){
				res.add(new UserForm(f.getSender()));
			}else if(f.getSender().getId() == loggedUser.getId()){
				res.add(new UserForm(f.getReciever()));
			}
		}
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/accept", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<List<UserForm>> acceptRequest(@RequestBody UserForm user){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedUser = userService.findOneByEmail(auth.getName());
		User sender = userService.findOneByEmail(user.getEmail());
		List<Friendship> requests = friendshipService.friedshipRequests();
		
		for(Friendship f : requests){
			if(f.getSender().getId() == sender.getId() && f.getReciever().getId() == loggedUser.getId()){
				f.setAccepted(2);
				friendshipService.save(f);
				return getRequests();
			}
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/reject", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<List<UserForm>> rejectRequest(@RequestBody UserForm user){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedUser = userService.findOneByEmail(auth.getName());
		User sender = userService.findOneByEmail(user.getEmail());
		List<Friendship> requests = friendshipService.friedshipRequests();
		
		for(Friendship f : requests){
			if(f.getSender().getId() == sender.getId() && f.getReciever().getId() == loggedUser.getId()){
				f.setAccepted(1);
				friendshipService.save(f);
				return getRequests();
			}
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/unfriend", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<List<UserForm>> unfriend(@RequestBody UserForm user){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedUser = userService.findOneByEmail(auth.getName());
		User sender = userService.findOneByEmail(user.getEmail());
		List<Friendship> requests = friendshipService.acceptedFriendships();
		
		for(Friendship f : requests){
			if((f.getSender().getId() == sender.getId() && f.getReciever().getId() == loggedUser.getId())
					|| (f.getSender().getId() == loggedUser.getId() && f.getReciever().getId() == sender.getId())){
				f.setAccepted(1);
				friendshipService.save(f);
				return getFriends();
			}
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
