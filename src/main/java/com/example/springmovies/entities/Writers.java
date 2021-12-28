package com.example.springmovies.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Writers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany
    @JoinTable(name = "writersinmovies",
            joinColumns = @JoinColumn(name = "id_writer"),
            inverseJoinColumns = @JoinColumn(name = "id_movie"))
    private Set<Movies> moviesSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movies> getMoviesSet() {
        return moviesSet;
    }

    public void setMoviesSet(Set<Movies> moviesSet) {
        this.moviesSet = moviesSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writers writers = (Writers) o;
        return Objects.equals(id, writers.id) && Objects.equals(name, writers.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Writers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
