package com.sg.ngs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sg.ngs.dto.Movie;
import com.sg.ngs.service.MovieServ;

@Controller
public class MovieController {
	
	@Autowired
	private MovieServ service;

	@RequestMapping
	public Movie saveMovie(@RequestBody Movie movie) {
		service.saveMovie(movie);
		return movie;
	}
	
}
