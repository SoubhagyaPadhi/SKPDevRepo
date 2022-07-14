package com.skp.demo.domain;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Document
@Validated
public class MovieInfo {
    @Id
    private String movieInfoId;
    @NotBlank(message = "movieInfo.name must be present")
    private String name;
    @NotNull
    @Positive(message = "movieInfo.year must be a Positive Value")
    private Integer year;

    @NotNull
    private List<@NotBlank(message = "movieInfo.cast must be present") String> cast;
    private LocalDate release_date;
	public String getMovieInfoId() {
		return movieInfoId;
	}
	public void setMovieInfoId(String movieInfoId) {
		this.movieInfoId = movieInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public List<String> getCast() {
		return cast;
	}
	public void setCast(List<String> cast) {
		this.cast = cast;
	}
	public LocalDate getRelease_date() {
		return release_date;
	}
	public void setRelease_date(LocalDate release_date) {
		this.release_date = release_date;
	}
	public MovieInfo(String movieInfoId, @NotBlank(message = "movieInfo.name must be present") String name,
			@NotNull @Positive(message = "movieInfo.year must be a Positive Value") Integer year,
			@NotNull List<@NotBlank(message = "movieInfo.cast must be present") String> cast, LocalDate release_date) {
		super();
		this.movieInfoId = movieInfoId;
		this.name = name;
		this.year = year;
		this.cast = cast;
		this.release_date = release_date;
	}
	public MovieInfo() {
		super();
	}
	@Override
	public String toString() {
		return "MovieInfo [movieInfoId=" + movieInfoId + ", name=" + name + ", year=" + year + ", cast=" + cast
				+ ", release_date=" + release_date + "]";
	}
	
	
    
    
    
}
