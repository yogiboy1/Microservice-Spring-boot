package com.microservice.business;

import org.springframework.stereotype.Service;

import com.microservice.data.TourPackage;
import com.microservice.repository.TourPackageRepository;

@Service
public class TourPackageService {
	private final TourPackageRepository tourPackageRepository;

	public TourPackageService(TourPackageRepository tourPackageRepository) {
		super();
		this.tourPackageRepository = tourPackageRepository;
	}
	
	public 	TourPackage CreateNewTourPackage(String code,String name) {
		return tourPackageRepository.findById(code).orElse(tourPackageRepository.save(new TourPackage(code, name)));
	}
	
	public Iterable<TourPackage> lookup(){
		return this.tourPackageRepository.findAll();
	}
	
	public long total() {
		return this.tourPackageRepository.count();
	}
}
