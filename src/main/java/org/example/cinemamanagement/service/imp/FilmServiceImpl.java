package org.example.cinemamanagement.service.imp;

import org.example.cinemamanagement.dto.FilmDTO;
import org.example.cinemamanagement.dto.TagDTO;
import org.example.cinemamanagement.model.Film;
import org.example.cinemamanagement.model.Tag;
import org.example.cinemamanagement.repository.FilmRepository;
import org.example.cinemamanagement.repository.TagRepository;
import org.example.cinemamanagement.request.AddFilmRequest;
import org.example.cinemamanagement.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {
    TagRepository tagRepository;
    FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(TagRepository tagRepository, FilmRepository filmRepository) {
        this.tagRepository = tagRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmDTO getFilmById(Long id) {
        return null;
    }

    @Override
    public FilmDTO addFilm(AddFilmRequest addFilmRequest) {
        Optional<Film> film = filmRepository.findFilmByTitle(addFilmRequest.getTitle());
        if (film.isPresent()) {
            throw new RuntimeException("Film already exists");
        }

        List<Tag> tags = addFilmRequest.getTags().stream().map(tag -> {
            Optional<Tag> tagOptional = tagRepository.findByName(tag.getName());
            if (tagOptional.isPresent()) {
                return tagOptional.get();
            }
            return tagRepository.save(Tag.builder().name(tag.getName()).build());
        }).collect(Collectors.toList());

        Film tempFilm = filmRepository.save(
                Film
                        .builder()
                        .title(addFilmRequest.getTitle())
                        .director(addFilmRequest.getDirector())
                        .country(addFilmRequest.getCountry())
                        .releaseDate(addFilmRequest.getReleaseDate())
                        .restrictAge(addFilmRequest.getRestrictAge())
                        .tags(tags)
                        .build());


        return FilmDTO
                .builder()
                .id(tempFilm.getId())
                .title(tempFilm.getTitle())
                .director(tempFilm.getDirector())
                .country(tempFilm.getCountry())
                .releaseDate(tempFilm.getReleaseDate())
                .restrictAge(tempFilm.getRestrictAge())
                .tags(tempFilm.getTags().stream().map(tag -> TagDTO
                        .builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .build())
                        .collect(Collectors.toList()))
                .build();

    }

    @Override
    public FilmDTO updateFilm(FilmDTO filmDTO) {
        return null;
    }

    @Override
    public void deleteFilm(UUID id) {

    }

    @Override
    public List<FilmDTO> getAllFilms() {
        return null;
    }
}
