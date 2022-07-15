package com.skp.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class Review {

    private String reviewId;
    private Long movieInfoId;
    private String comment;
    //@Min(value = 0L, message = "rating.negative : rating is negative and please pass a non-negative value")
    private Double rating;
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
	public Review(String reviewId, Long movieInfoId, String comment, Double rating) {
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
    
    
}
