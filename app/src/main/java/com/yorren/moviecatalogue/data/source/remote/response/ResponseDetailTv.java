package com.yorren.moviecatalogue.data.source.remote.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDetailTv{

	@SerializedName("first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("genres")
	private List<GenresItem> genres;

	@SerializedName("popularity")
	private String popularity;

	@SerializedName("vote_average")
	private String voteAverage;

	@SerializedName("name")
	private String name;

	@SerializedName("tagline")
	private String tagline;

	@SerializedName("id")
	private String id;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("original_language")
	private String language;

	public String getBackdropPath() {
		return backdropPath;
	}

	public String getLanguage() {
		return language;
	}

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public String getOverview(){
		return overview;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	public String getPopularity(){
		return popularity;
	}

	public String getVoteAverage(){
		return voteAverage;
	}

	public String getName(){
		return name;
	}

	public String getTagline(){
		return tagline;
	}

	public String getId(){
		return id;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public ResponseDetailTv(String firstAirDate, String overview, List<GenresItem> genres, String popularity, String voteAverage, String name, String tagline, String id, String posterPath, String backdropPath, String language) {
		this.firstAirDate = firstAirDate;
		this.overview = overview;
		this.genres = genres;
		this.popularity = popularity;
		this.voteAverage = voteAverage;
		this.name = name;
		this.tagline = tagline;
		this.id = id;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.language = language;
	}
}