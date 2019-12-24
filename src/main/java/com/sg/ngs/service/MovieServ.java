package com.sg.ngs.service;

import java.util.List;

import com.sg.ngs.dto.Movie;

public interface MovieServ {

	public List<Movie> findByDirector();

	public void clearCache();


	public List<Movie> getMovies();

	public void saveMovie(Movie movie);
	
}
