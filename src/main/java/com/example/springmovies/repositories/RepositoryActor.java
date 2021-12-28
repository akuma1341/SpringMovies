package com.example.springmovies.repositories;

import com.example.springmovies.entities.Actors;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryActor extends CrudRepository<Actors, Integer>{

}
