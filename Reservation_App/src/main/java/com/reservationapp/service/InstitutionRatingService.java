package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Institution;
import com.reservationapp.model.InstitutionRating;
import com.reservationapp.model.User;

public interface InstitutionRatingService {

	InstitutionRating findOne(Long id);

	List<InstitutionRating> findAll();
	
	InstitutionRating save(InstitutionRating institutionRating);
	
	List<InstitutionRating> save(List<InstitutionRating> institutionRatings);
	
	InstitutionRating delete(Long id);
	
	void delete(List<Long> ids);
	
	List<InstitutionRating> searchByInstitution(Institution institution);
	
	double calculateRating(List<InstitutionRating> ratings);
	
	InstitutionRating findByInstitutionAndUser(Institution institution, User user);
	
} 
