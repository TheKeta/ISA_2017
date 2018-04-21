package com.reservationapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Show implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableGenerator(initialValue = 10000, name = "asd")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(optional = false)
	private Genre genre;

	@ManyToOne(optional = false)
	private ShowType type;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String cast;

	@Column(nullable = false)
	private int length;

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

	public Show() {

	}

	public Show(String name, Genre genre, ShowType type, String description, String cast, int length , MultipartFile picture) {
		super();
		this.name = name;
		this.genre = genre;
		this.type = type;
		this.length = length;
		this.cast = cast;
		this.description = description;
		this.picture = picture;
	}
	
	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public ShowType getType() {
		return type;
	}

	public void setType(ShowType type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
