package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilmRepository extends JpaRepository<Film, UUID> {
    public Optional<Film> findFilmByTitle(String title);
    public void deleteById(UUID id);
}
