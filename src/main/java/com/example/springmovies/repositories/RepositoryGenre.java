package com.example.springmovies.repositories;

import com.example.springmovies.entities.Genres;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryGenre extends CrudRepository<Genres, Integer>{
}
