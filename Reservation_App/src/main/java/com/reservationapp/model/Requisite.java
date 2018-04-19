package com.reservationapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Requisite implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@TableGenerator(name = "Address_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "Addr_Gen", initialValue = 10000, allocationSize = 100)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Address_Gen")
	private Long id;
	
	@Version
	private Long version;
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Column(nullable = false)
	private Long creator; //user's ID
	
	@Column(nullable = false)
	private String name;
	
	@JsonInclude()
	@Transient
	private MultipartFile picture;

	@Column(nullable = true)
	@Lob
	private byte[] pictureDB;
	
	public byte[] getPictureDB() {
		return pictureDB;
	}

	public void setPictureDB(byte[] pictureDB) {
		this.pictureDB = pictureDB;
	}

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false)
	private String type; // new or used
	
	@Column(nullable = false)
	private Date endDate;

	public Requisite(Long id, Long creator, String name,MultipartFile picture, String description, int price, String type, Date endDate) {
		super();
		Assert.notNull(name, "Name can not be null");
		Assert.notNull(description, "Description can not be null");
		Assert.notNull(price, "Price can not be null");
		Assert.notNull(endDate, "End Date can not be null");
		this.id = id;
		this.creator = creator;
		this.name = name;
		this.picture = picture;
		this.description = description;
		this.price = price;
		this.type = type;
		this.endDate = endDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Requisite() {}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
