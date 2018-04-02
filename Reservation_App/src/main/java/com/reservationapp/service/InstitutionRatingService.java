package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.InstitutionRating;

public interface InstitutionRatingService {

	InstitutionRating findOne(Long id);

	List<InstitutionRating> findAll();
	
	InstitutionRating save(InstitutionRating institutionRating);
	
	List<InstitutionRating> save(List<InstitutionRating> institutionRatings);
	
	InstitutionRating delete(Long id);
	
	void delete(List<Long> ids);
}
