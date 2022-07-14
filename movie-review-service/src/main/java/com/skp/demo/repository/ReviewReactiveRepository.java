package com.skp.demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.skp.demo.domain.Review;

import reactor.core.publisher.Flux;

public interface ReviewReactiveRepository extends ReactiveMongoRepository<Review, String> {

    //Flux<Review> findReviewsByMovieInfoId(String reviewId);

    Flux<Review> findReviewsByMovieInfoId(Long movieInfoId);
}
