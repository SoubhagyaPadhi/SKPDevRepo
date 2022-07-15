package com.skp.demo.util;

import java.time.Duration;

import com.skp.demo.exception.MoviesInfoServerException;
import com.skp.demo.exception.ReviewsServerException;

import reactor.core.Exceptions;
import reactor.util.retry.Retry;
import reactor.util.retry.RetrySpec;

public class RetryUtil {


    public static Retry retrySpec() {
        return RetrySpec.fixedDelay(3, Duration.ofSeconds(1))
                .filter((ex) -> ex instanceof MoviesInfoServerException || ex instanceof ReviewsServerException)
                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) -> Exceptions.propagate(retrySignal.failure())));

    }
}
