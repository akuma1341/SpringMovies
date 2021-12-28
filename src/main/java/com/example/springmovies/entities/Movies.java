package com.example.springmovies.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private double rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_director", referencedColumnName = "id")
    private Directors director;

    @ManyToMany
    @JoinTable(name = "actorsinmovies",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_actor"))
    private Set<Actors> actorsSet;

    @ManyToMany
    @JoinTable(name = "genresinmovies",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private Set<Genres> genresSet;

    @ManyToMany
    @JoinTable(name = "writersinmovies",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_writer"))
    private Set<Writers> writersSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Directors getDirector() {
        return director;
    }

    public void setDirector(Directors idDirector) {
        this.director = idDirector;
    }

    public Set<Actors> getActorsSet() {
        return actorsSet;
    }

    public void setActorsSet(Set<Actors> actorsSet) {
        this.actorsSet = actorsSet;
    }

    public Set<Genres> getGenresSet() {
        return genresSet;
    }

    public void setGenresSet(Set<Genres> genresSet) {
        this.genresSet = genresSet;
    }

    public Set<Writers> getWritersSet() {
        return writersSet;
    }

    public void setWritersSet(Set<Writers> writersSet) {
        this.writersSet = writersSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return id == movies.id && Double.compare(movies.rating, rating) == 0 && Objects.equals(title, movies.title) && Objects.equals(description, movies.description) && Objects.equals(director, movies.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, rating, director);
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", director=" + director +
                '}';
    }
}
