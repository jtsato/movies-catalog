package io.github.jtsato.moviescatalog.core.domains.movie.xcutting;

import io.github.jtsato.moviescatalog.core.domains.movie.model.Movie;

import java.util.Optional;

@FunctionalInterface
public interface GetRandomMovieGateway {

    Optional<Movie> execute(int index);
}