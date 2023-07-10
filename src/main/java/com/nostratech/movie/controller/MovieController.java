package com.nostratech.movie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nostratech.movie.dto.MovieCreateDTO;
import com.nostratech.movie.dto.MovieDetailDTO;
import com.nostratech.movie.service.MovieService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/movie")
public class MovieController {

	private final MovieService MovieService;

	@GetMapping("/list")
	public String findMovieList(Model model) {
		List<MovieDetailDTO> movies = MovieService.findMovieListDetail();
		model.addAttribute("movies", movies);
		return "movie/list";
	}

	@GetMapping("/new")
	public String loadMovieForm(Model model) {
		MovieCreateDTO dto = new MovieCreateDTO();
		model.addAttribute("movieCreateDTO", dto);
		return "movie/movie-new";
	}

	@PostMapping("/new")
	public String addNewMovie(@ModelAttribute("movieCreateDTO") @Valid MovieCreateDTO dto, 
			BindingResult bindingResult,
			Errors errors,
			Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("movieCreateDTO", dto);
			return "movie/movie-new";
		}
		MovieService.createNewMovie(dto);
		return "redirect:/movie/list";

	}

}
