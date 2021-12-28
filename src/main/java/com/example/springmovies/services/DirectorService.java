package com.example.springmovies.services;

import com.example.springmovies.entities.Directors;
import com.example.springmovies.repositories.RepositoryDirectors;

public class DirectorService {
    private RepositoryDirectors directors;

    public Iterable<Directors> findAll() {
        return directors.findAll();
    }

    public Directors findById(int id) {
        Directors director = null;
        if (directors.findById(id).isPresent()) {
            director = directors.findById(id).get();
        }
        return director;
    }
}
