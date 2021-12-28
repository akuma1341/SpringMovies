package com.example.springmovies.repositories;

import com.example.springmovies.entities.Writers;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryWriter extends CrudRepository<Writers, Integer>{
}
