package com.reservationapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bid implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Long itemsID;
	
	@Column(nullable = false)
	private Long biddersID;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private boolean reservation; //true for reservation
	
	

}
