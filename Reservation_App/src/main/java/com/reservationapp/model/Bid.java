package com.reservationapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.util.Assert;

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
	
	@Version
	private Long version;
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Bid(Long id, Long itemsID, Long biddersID, int price, boolean reservation) {
		super();
		Assert.notNull(itemsID, "Name can not be null");
		Assert.notNull(biddersID, "Name can not be null");
		Assert.notNull(price, "Name can not be null");
		Assert.notNull(reservation, "Name can not be null");
		this.id = id;
		this.itemsID = itemsID;
		this.biddersID = biddersID;
		this.price = price;
		this.reservation = reservation;
	}
	
	public Bid() {}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemsID() {
		return itemsID;
	}

	public void setItemsID(Long itemsID) {
		this.itemsID = itemsID;
	}

	public Long getBiddersID() {
		return biddersID;
	}

	public void setBiddersID(Long biddersID) {
		this.biddersID = biddersID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isReservation() {
		return reservation;
	}

	public void setReservation(boolean reservation) {
		this.reservation = reservation;
	}

	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private boolean reservation; //true for reservation
	
	

}
