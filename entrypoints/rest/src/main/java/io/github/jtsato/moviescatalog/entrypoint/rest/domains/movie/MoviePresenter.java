package io.github.jtsato.moviescatalog.entrypoint.rest.domains.movie;

import io.github.jtsato.moviescatalog.core.domains.movie.model.Movie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Jorge Takeshi Sato
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoviePresenter {

    public static MovieResponse of(final Movie movie) {
        return new MovieResponse(movie.id(), movie.imdbId(), movie.title(), movie.year(), movie.genre(), movie.imdbRating(), movie.imdbVotes(), movie.score(), movie.posterUrl());
    }
}
