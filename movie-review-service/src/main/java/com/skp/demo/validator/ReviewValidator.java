package com.skp.demo.validator;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.skp.demo.domain.Review;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReviewValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Review.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"movieInfoId", "movieInfoId.null", "Pass a valid movieInfoId" );
        ValidationUtils.rejectIfEmpty(errors,"rating", "rating.null", "Pass a valid rating" );
        Review review = (Review) target;
        //log.info("Review : {}" , review);
        if(review.getRating()!=null && review.getRating()<0.0){
            errors.rejectValue("rating", "rating.negative", "rating is negative and please pass a non-negative value");
        }

    }
}
