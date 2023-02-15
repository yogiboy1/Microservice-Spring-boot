package com.microservice.business;

import org.springframework.stereotype.Service;

import com.microservice.data.Difficulty;
import com.microservice.data.Region;
import com.microservice.data.Tour;
import com.microservice.data.TourPackage;
import com.microservice.repository.TourPackageRepository;
import com.microservice.repository.TourRepository;

@Service
public class TourService{
	private final TourRepository tourRepository;
	private final TourPackageRepository tourPackageRepository;
	public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
		super();
		this.tourRepository = tourRepository;
		this.tourPackageRepository = tourPackageRepository;
	}

	public Tour CreateTour(String title, String description, String blurb, Integer price, String duration, String bullets,
                String keywords, String TourPackageName , Difficulty difficulty, Region region) {
		TourPackage tourPackage = this.tourPackageRepository.findByName(TourPackageName)
				.orElseThrow(()->new RuntimeException("Tour Pakcage missing or does not exist"+TourPackageName));
		
		return this.tourRepository.save(new Tour(title, description, blurb, price, duration, bullets, keywords, tourPackage, difficulty, region));	
	}
	
	public long total() {
		return this.tourRepository.count();
	}

}
