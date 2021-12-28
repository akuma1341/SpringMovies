package com.example.springmovies.repositories;

import com.example.springmovies.entities.Movies;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryMovie extends CrudRepository<Movies, Integer> {
}
