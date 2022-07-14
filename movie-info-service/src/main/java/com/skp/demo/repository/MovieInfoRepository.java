package com.skp.demo.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.skp.demo.domain.MovieInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo, String> {

    Flux<MovieInfo> findByYear(Integer year);

    Mono<MovieInfo> findByName(String name);
}
