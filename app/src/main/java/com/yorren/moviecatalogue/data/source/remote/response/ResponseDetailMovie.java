package com.yorren.moviecatalogue.data.source.remote.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDetailMovie{

	@SerializedName("overview")
	private String overview;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("genres")
	private List<GenresItem> genres;

	@SerializedName("vote_average")
	private String voteAverage;

	@SerializedName("tagline")
	private String tagline;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("original_language")
	private String language;

	@SerializedName("popularity")
	private String popularity;

	public String getBackdropPath() {
		return backdropPath;
	}

	public String getLanguage() {
		return language;
	}

	public String getPopularity() {
		return popularity;
	}

	public String getOverview(){
		return overview;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	public String getVoteAverage(){
		return voteAverage;
	}

	public String getTagline(){
		return tagline;
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

	public ResponseDetailMovie(String overview, String releaseDate, List<GenresItem> genres, String voteAverage, String tagline, String id, String title, String posterPath, String backdropPath, String language, String popularity) {
		this.overview = overview;
		this.releaseDate = releaseDate;
		this.genres = genres;
		this.voteAverage = voteAverage;
		this.tagline = tagline;
		this.id = id;
		this.title = title;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.language = language;
		this.popularity = popularity;
	}
}