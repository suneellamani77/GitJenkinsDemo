package com.sg.ngs.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Component;

import com.sg.ngs.dto.Movie;

@Component
public class MovieServImpl implements MovieServ {
	
	private static List<Movie> movies;
	private List<Movie> temp = new ArrayList<>();
	
	static {
		movies = getDummyData(movies);
	}
	
	@Override
	@Cacheable(value = "movieFindCache")
	public List<Movie> findByDirector() {
		List<Movie> mList= getMovies();
		slowQuery(2000L);
		return mList;
	}

	private static List<Movie> getDummyData(List<Movie> movies2) {
		movies2 = new ArrayList<>();
		movies2.add(new Movie(1, "Bahubali", "RajaMouli"));
		movies2.add(new Movie(2, "KGF", "Prashant"));
		movies2.add(new Movie(3, "Singham", "Yash"));
		return movies2;
	}

	private void slowQuery(long seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
	
	@Override
	@CacheEvict(value = "movieFindCache", allEntries=true)
	public void clearCache() {
	}
	
	
	@Override
	@Caching(
		      put = {
		   @CachePut(value = "movieFindCache", key = "'name:' + #result.name", condition = "#result.name != null"),
           //@CachePut(value = "movieFindCache", key = "#result.id", condition = "#result != null")
     }
	)
	public List<Movie> getMovies() {
		System.out.println("findByDirector is running inside the @cacheput...");
		return new ArrayList<>(movies);
	}

	@Override
	public void saveMovie(Movie movie) {
	}
}
