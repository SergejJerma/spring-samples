package com.serjer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serjer.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

}
