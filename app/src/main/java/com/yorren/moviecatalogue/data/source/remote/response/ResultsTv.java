package com.yorren.moviecatalogue.data.source.remote.response;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class ResultsTv {

	@SerializedName("first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("original_language")
	private String language;

	@SerializedName("popularity")
	private String popularity;

	@SerializedName("vote_average")
	private String userScore;

	public String getUserScore() {
		return userScore;
	}

	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public String getOverview(){
		return overview;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public ResultsTv(String firstAirDate, String overview, String name, String id, String posterPath, String backdropPath, String language, String popularity, String userScore) {
		this.firstAirDate = firstAirDate;
		this.overview = overview;
		this.name = name;
		this.id = id;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.language = language;
		this.popularity = popularity;
		this.userScore = userScore;
	}

	public void setFirstAirDate(String firstAirDate) {
		this.firstAirDate = firstAirDate;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
}