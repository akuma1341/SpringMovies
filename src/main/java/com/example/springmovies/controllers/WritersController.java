package com.example.springmovies.controllers;

import com.example.springmovies.entities.Writers;
import com.example.springmovies.repositories.RepositoryWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/writers")
public class WritersController {
    @Autowired
    private RepositoryWriter writerRepository;

    @GetMapping
    public String allWriters(Model model) {
        model.addAttribute("writers", writerRepository.findAll());
        return "allWriters";
    }

    @GetMapping("/{id}")
    public String writerByID(@PathVariable(name = "id") int id, Model model) {
        if (writerRepository.findById(id).isPresent()) {
            model.addAttribute("writer", writerRepository.findById(id).get());
            return "writer";
        } else {
            return "redirect:/writers";
        }
    }

    @GetMapping("{id}/editWriter")
    public String edit(@PathVariable(name = "id") int id, Model model) {
        if (writerRepository.findById(id).isPresent()) {
            model.addAttribute("writer", writerRepository.findById(id).get());
            return "editWriter";
        } else {
            return "redirect:/writers";
        }
    }

    @PostMapping("/{id}/updateWriter")
    public String update(@PathVariable(name = "id") int id, @ModelAttribute("writer") Writers writer) {
        if (writerRepository.findById(id).isPresent()) {
            writerRepository.save(writer);
        }
        return "redirect:/writers";
    }

    @GetMapping("/new")
    public String newWriterForm(@ModelAttribute("writer") Writers writer) {
        return "newWriterForm";
    }

    @PostMapping("/createWriter")
    public String createWriter(@ModelAttribute("writer") Writers writer) {
        writerRepository.save(writer);
        return "redirect:/writers";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable(name = "id") int id) {
        if (writerRepository.findById(id).isPresent()) {
            Writers writer = writerRepository.findById(id).get();
            writerRepository.delete(writer);
        }
        return "redirect:/writers";
    }
}
