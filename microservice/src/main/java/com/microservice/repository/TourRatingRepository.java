package com.microservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.microservice.data.TourRating;
import com.microservice.data.TourRatingPk;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {

    List<TourRating> findByPkTourId(Integer tourId);


    Optional<TourRating> findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);
    
    Page<TourRating> findByPkTourId(Integer tourId,Pageable pageable);
}