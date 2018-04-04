package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Institution;
import com.reservationapp.model.InstitutionRating;
import com.reservationapp.repository.InstitutionRatingRepository;
import com.reservationapp.service.InstitutionRatingService;

@Service
@Transactional
public class InstitutionRatingServiceImpl implements InstitutionRatingService{

	@Autowired
	private InstitutionRatingRepository institutionRatingRepository;

	@Override
	public InstitutionRating findOne(Long id) {
		return institutionRatingRepository.findById(id).get();
	}
	
	@Override
	public List<InstitutionRating> findAll() {
		return institutionRatingRepository.findAll();
	}

	@Override
	public InstitutionRating save(InstitutionRating institutionRating) {
		return institutionRatingRepository.save(institutionRating);
	}

	@Override
	public List<InstitutionRating> save(List<InstitutionRating> institutionRating) {
		return institutionRatingRepository.saveAll(institutionRating);
	}

	@Override
	public InstitutionRating delete(Long id) {
		InstitutionRating institutionRating = institutionRatingRepository.findById(id).get();
		if(institutionRating == null){
			throw new IllegalArgumentException("Tried to delete" 
					+ "non-existant user");
		}
		institutionRatingRepository.delete(institutionRating);
		return institutionRating;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public List<InstitutionRating> searchByInstitution(Institution institution) {
		return institutionRatingRepository.searchByInstitution(institution);
	}

	@Override
	public double calculateRating(List<InstitutionRating> ratings) {
		double sum = 0;
		for(InstitutionRating rating : ratings) {
			sum += rating.getRating();
		}
		return sum/ratings.size();
	}
	
}
