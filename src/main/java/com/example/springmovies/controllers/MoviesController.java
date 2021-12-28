package com.example.springmovies.controllers;

import com.example.springmovies.entities.Actors;
import com.example.springmovies.entities.Movies;
import com.example.springmovies.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movies")
public class MoviesController {
    @Autowired
    private RepositoryMovie moviesRepository;

    @Autowired
    private RepositoryDirectors directors;

    @Autowired
    private RepositoryActor actors;

    @Autowired
    private RepositoryGenre genres;

    @Autowired
    private RepositoryWriter writers;

    @GetMapping
    public String showAllMovies(Model model) {
        model.addAttribute("movies", moviesRepository.findAll());
        return "allMovies";
    }

    @GetMapping("/{id}")
    public String movieByID(@PathVariable("id") int id, Model model) {
        if (moviesRepository.findById(id).isPresent()) {
            model.addAttribute("movie", moviesRepository.findById(id).get());
            return "movie";
        } else {
            return "redirect:/movies";
        }
    }

    @GetMapping("/{id}/editMovie")
    public String edit(@PathVariable("id") int id, Model model) {
        if (moviesRepository.findById(id).isPresent()) {
            model.addAttribute("movie", moviesRepository.findById(id).get());
            model.addAttribute("actors", actors.findAll());
            model.addAttribute("genres", genres.findAll());
            model.addAttribute("writers", writers.findAll());
            model.addAttribute("directors", directors.findAll());
            return "editMovie";
        } else {
            return "redirect:/movies";
        }
    }

    @PostMapping("/{id}/updateMovie")
    public String update(@PathVariable("id") int id, @ModelAttribute("movie") Movies movie) {
        if (moviesRepository.findById(id).isPresent()) {
            moviesRepository.save(movie);
        }
        return "redirect:/movies";
    }

    @GetMapping("/new")
    public String newMovieForm(@ModelAttribute("movie") Movies movie, Model model) {
        model.addAttribute("actors", actors.findAll());
        model.addAttribute("genres", genres.findAll());
        model.addAttribute("writers", writers.findAll());
        model.addAttribute("directors", directors.findAll());
        return "newMovieForm";
    }

    @PostMapping("/createMovie")
    public String createMovie(@ModelAttribute("movie") Movies movie) {
        moviesRepository.save(movie);
        return "redirect:/movies";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        if (moviesRepository.findById(id).isPresent()) {
            Movies movie = moviesRepository.findById(id).get();
            moviesRepository.delete(movie);
        }
        return "redirect:/movies";
    }
}
