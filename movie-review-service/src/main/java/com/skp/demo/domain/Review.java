package com.skp.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
public class Review {

    @Id
    private String reviewId;
    @jakarta.validation.constraints.NotNull(message = "rating.movieInfoId : must not be null")
    private Long movieInfoId;
    private String comment;
    @jakarta.validation.constraints.Min(value = 0L, message = "rating.negative : please pass a non-negative value")
    private Double rating;
    
    
	public Review(String reviewId, @NotNull(message = "rating.movieInfoId : must not be null") Long movieInfoId,
			String comment,
			@Min(value = 0, message = "rating.negative : please pass a non-negative value") Double rating) {
		super();
		this.reviewId = reviewId;
		this.movieInfoId = movieInfoId;
		this.comment = comment;
		this.rating = rating;
	}


	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getReviewId() {
		return reviewId;
	}


	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}


	public Long getMovieInfoId() {
		return movieInfoId;
	}


	public void setMovieInfoId(Long movieInfoId) {
		this.movieInfoId = movieInfoId;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Double getRating() {
		return rating;
	}


	public void setRating(Double rating) {
		this.rating = rating;
	}


	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", movieInfoId=" + movieInfoId + ", comment=" + comment + ", rating="
				+ rating + "]";
	}
	
	
    
    
}
