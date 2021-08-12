package com.yorren.moviecatalogue.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class ResultsMovie {

	@SerializedName("original_title")
	private String originalTitle;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("overview")
	private String overview;

	@SerializedName("vote_average")
	private String voteAverage;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("original_language")
	private String language;

	@SerializedName("popularity")
	private String popularity;

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

	public String getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(String voteAverage) {
		this.voteAverage = voteAverage;
	}

	public String getOverview() {
		return overview;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public ResultsMovie(String originalTitle, String releaseDate, String id, String title, String posterPath, String overview, String voteAverage, String backdropPath, String language, String popularity) {
		this.originalTitle = originalTitle;
		this.releaseDate = releaseDate;
		this.id = id;
		this.title = title;
		this.posterPath = posterPath;
		this.overview = overview;
		this.voteAverage = voteAverage;
		this.backdropPath = backdropPath;
		this.language = language;
		this.popularity = popularity;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}
}