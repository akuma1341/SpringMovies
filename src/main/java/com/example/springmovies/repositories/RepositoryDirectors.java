package com.example.springmovies.repositories;

import com.example.springmovies.entities.Directors;
import org.springframework.data.repository.CrudRepository;
public interface RepositoryDirectors extends CrudRepository<Directors, Integer>{
}
