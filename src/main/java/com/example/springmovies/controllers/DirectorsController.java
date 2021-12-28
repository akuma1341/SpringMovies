package com.example.springmovies.controllers;

import com.example.springmovies.entities.Directors;
import com.example.springmovies.repositories.RepositoryDirectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/directors")
public class DirectorsController {
    @Autowired
    private RepositoryDirectors directorsRepository;

    @GetMapping
    public String allDirectors(Model model) {
        model.addAttribute("directors", directorsRepository);
        return "allDirectors";
    }

    @GetMapping("/{id}")
    public String directorByID(@PathVariable("id") int id, Model model) {
        if (directorsRepository.findById(id).isPresent()) {
            model.addAttribute("director", directorsRepository.findById(id).get());
            return "director";
        } else {
            return "redirect:/directors";
        }
    }

    @GetMapping("/{id}/editDirector")
    public String edit(@PathVariable(name = "id") int id, Model model) {
        if (directorsRepository.findById(id).isPresent()) {
            model.addAttribute("director", directorsRepository.findById(id).get());
            return "editDirector";
        } else {
            return "redirect:/directors";
        }
    }

    @PostMapping("/{id}/updateDirector")
    public String update(@PathVariable(name = "id") int id, @ModelAttribute("director") Directors director) {
        if (directorsRepository.findById(id).isPresent()) {
            directorsRepository.save(director);
        }
        return "redirect:/directors";
    }

    @GetMapping("/new")
    public String newDirectorForm(@ModelAttribute("director") Directors director) {
        return "newDirectorForm";
    }

    @PostMapping("/createDirector")
    public String createDirector(@ModelAttribute("director") Directors director) {
        directorsRepository.save(director);
        return "redirect:/directors";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable(name = "id") int id) {
        if (directorsRepository.findById(id).isPresent()) {
            Directors director = directorsRepository.findById(id).get();
            directorsRepository.delete(director);
        }
        return "redirect:/directors";
    }
}
