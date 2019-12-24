package com.sg.ngs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.ngs.dto.Movie;
import com.sg.ngs.service.MovieServ;

@Controller
public class NGSController {
	
	@Autowired
	private MovieServ service;

	@RequestMapping(value = {"/","/home"})
	@ResponseBody
	public String home() {
		List<Movie> movie = service.findByDirector();
		movie.forEach(System.out::println);
		return "Spring MVC is worked......";
	}
	
	@RequestMapping(value="/cache/{director}")
	@ResponseBody
	public String finfByDirector(@PathVariable("director") String director) {
		List<Movie> movies = service.findByDirector();
		Movie movie= movies.stream()
				.filter(m -> m.getDirector().equalsIgnoreCase(director))
				.findAny()
				.orElse(null);
		
		return movie.getName();
	}
	
	@RequestMapping(value="/cache/{director}")
	@ResponseBody
	public String finfByDirectorCachePut(@PathVariable("director") String director) {
		List<Movie> movies = service.getMovies();
		Movie movie= movies.stream()
				.filter(m -> m.getDirector().equalsIgnoreCase(director))
				.findAny()
				.orElse(null);
		
		return movie.getName();
	}
	
	@RequestMapping("/recache")
	@ResponseBody
	public String resetCache() {
		service.clearCache();
		return "cache has been cleared........";
	}
}
