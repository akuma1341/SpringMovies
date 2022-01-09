package com.example.springmovies.controllers;

import com.example.springmovies.entities.Genres;
import com.example.springmovies.repositories.RepositoryGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenresController {
    @Autowired
    private RepositoryGenre genreRepository;

    @GetMapping
    public String allGenres(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        return "allGenres";
    }

    @GetMapping("/{id}")
    public String genreByID(@PathVariable("id") int id, Model model) {
        if (genreRepository.findById(id).isPresent()) {
            model.addAttribute("genre", genreRepository.findById(id).get());
            return "genre";
        } else {
            return "redirect:/genres";
        }
    }

    @GetMapping("/{id}/editGenre")
    public String edit(@PathVariable(name = "id") int id, Model model) {
        if (genreRepository.findById(id).isPresent()) {
            model.addAttribute("genre", genreRepository.findById(id).get());
            return "editGenre";
        } else {
            return "redirect:/genres";
        }
    }

    @PostMapping("/{id}/updateGenre")
    public String update(@PathVariable(name = "id") int id, @ModelAttribute("genre") Genres genre) {
        genreRepository.save(genre);
        return "redirect:/genres";
    }

    @GetMapping("/new")
    public String newGenreForm(@ModelAttribute("genre") Genres genre) {
        return "newGenreForm";
    }

    @PostMapping("/createGenre")
    public String createNewActor(@ModelAttribute("genre") Genres genre) {
        genreRepository.save(genre);
        return "redirect:/genres";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable(name = "id") int id) {
        if (genreRepository.findById(id).isPresent()) {
            Genres genre = genreRepository.findById(id).get();
            genreRepository.delete(genre);
        }
        return "redirect:/genres";
    }
}
