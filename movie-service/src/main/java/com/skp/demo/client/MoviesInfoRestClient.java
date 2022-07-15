package com.skp.demo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.skp.demo.domain.MovieInfo;
import com.skp.demo.exception.MoviesInfoClientException;
import com.skp.demo.exception.MoviesInfoServerException;
import com.skp.demo.util.RetryUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MoviesInfoRestClient {

	private WebClient webClient;

	@Value("${restClient.moviesInfoUrl}")
	private String moviesInfoUrl;

	public MoviesInfoRestClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Mono<MovieInfo> retrieveMovieInfo(String movieId) {

		var url = moviesInfoUrl.concat("/{id}");
		/*
		 * var retrySpec = RetrySpec.fixedDelay(3, Duration.ofSeconds(1)) .filter((ex)
		 * -> ex instanceof MoviesInfoServerException)
		 * .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) ->
		 * Exceptions.propagate(retrySignal.failure())));
		 */

		return webClient.get().uri(url, movieId).retrieve().onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value() || status.value() == HttpStatus.BAD_REQUEST.value(), clientResponse -> {
			// log.info("Status code : {}", clientResponse.statusCode().value());
			System.out.println(clientResponse.statusCode().value());
			if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
				return Mono.error(new MoviesInfoClientException(
						"There is no MovieInfo available for the passed in Id : " + movieId,
						clientResponse.statusCode().value()));
			}
			return clientResponse.bodyToMono(String.class).flatMap(response -> Mono
					.error(new MoviesInfoClientException(response, clientResponse.statusCode().value())));
		}).onStatus(status -> status.value() == HttpStatus.INTERNAL_SERVER_ERROR.value(), clientResponse -> {
			// log.info("Status code : {}", clientResponse.statusCode().value());
			System.out.println(clientResponse.statusCode().value());
			return clientResponse.bodyToMono(String.class)
					.flatMap(response -> Mono.error(new MoviesInfoServerException(response)));
		}).bodyToMono(MovieInfo.class)
				// .retry(3)
				// .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(500)))
				.retryWhen(RetryUtil.retrySpec()).log();

	}

	public Flux<MovieInfo> retrieveMovieInfoStream() {

		var url = moviesInfoUrl.concat("/stream");
		/*
		 * var retrySpec = RetrySpec.fixedDelay(3, Duration.ofSeconds(1)) .filter((ex)
		 * -> ex instanceof MoviesInfoServerException)
		 * .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) ->
		 * Exceptions.propagate(retrySignal.failure())));
		 */

		return webClient.get().uri(url).retrieve().onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value() || status.value() == HttpStatus.BAD_REQUEST.value(), (clientResponse -> {
			// log.info("Status code : {}", clientResponse.statusCode().value());
			return clientResponse.bodyToMono(String.class).flatMap(response -> Mono
					.error(new MoviesInfoClientException(response, clientResponse.statusCode().value())));
		})).onStatus(status -> status.value() == HttpStatus.INTERNAL_SERVER_ERROR.value(), (clientResponse -> {
			//log.info("Status code : {}", clientResponse.statusCode().value());
			return clientResponse.bodyToMono(String.class)
					.flatMap(response -> Mono.error(new MoviesInfoServerException(response)));
		})).bodyToFlux(MovieInfo.class)
				// .retry(3)
				.retryWhen(RetryUtil.retrySpec()).log();

	}

	public Mono<MovieInfo> retrieveMovieInfo_exchange(String movieId) {

		var url = moviesInfoUrl.concat("/{id}");

		return webClient.get().uri(url, movieId).exchangeToMono(clientResponse -> {

			if (clientResponse.statusCode().is2xxSuccessful())
				return clientResponse.bodyToMono(MovieInfo.class);
			if (clientResponse.statusCode().is4xxClientError())
				return Mono.error(new MoviesInfoClientException(
						"There is no MovieInfo available for the passed in Id : " + movieId,
						clientResponse.statusCode().value()));

			return clientResponse.bodyToMono(String.class)
					.flatMap(response -> Mono.error(new MoviesInfoServerException(response)));

		}).retryWhen(RetryUtil.retrySpec()).log();

	}

}
