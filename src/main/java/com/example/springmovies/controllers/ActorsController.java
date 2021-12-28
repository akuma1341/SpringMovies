package com.example.springmovies.controllers;

import com.example.springmovies.entities.Actors;
import com.example.springmovies.repositories.RepositoryActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actors")
public class ActorsController {
    @Autowired
    private RepositoryActor actorRepository;

    @GetMapping
    public String allActors(Model model) {
        model.addAttribute("actors", actorRepository.findAll());
        return "allActors";
    }

    @GetMapping("/{id}")
    public String actorByID(@PathVariable("id") int id, Model model) {
        if (actorRepository.findById(id).isPresent()) {
            model.addAttribute("actor", actorRepository.findById(id).get());
            return "actor";
        } else {
            return "redirect:/actors";
        }
    }

    @GetMapping("/{id}/editActor")
    public String edit(@PathVariable(name = "id") int id, Model model) {
        if (actorRepository.findById(id).isPresent()) {
            model.addAttribute("actor", actorRepository.findById(id).get());
            return "editActor";
        } else {
            return "redirect:/actors";
        }
    }

    @PostMapping("/{id}/updateActor")
    public String update(@PathVariable(name = "id") int id, @ModelAttribute("actor") Actors actor) {
        if (actorRepository.findById(id).isPresent()) {
            actorRepository.save(actor);
        }
        return "redirect:/actors";
    }

    @GetMapping("/new")
    public String newActorForm(@ModelAttribute("actor") Actors actor) {
        return "newActorForm";
    }

    @PostMapping("/createActor")
    public String createActor(@ModelAttribute("actor") Actors actor) {
        actorRepository.save(actor);
        return "redirect:/actors";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable(name = "id") int id) {
        if (actorRepository.findById(id).isPresent()) {
            Actors actor = actorRepository.findById(id).get();
            actorRepository.delete(actor);
        }
        return "redirect:/actors";
    }
}
