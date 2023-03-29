package io.github.jtsato.moviescatalog.core.domains.movie.usecase;

import io.github.jtsato.moviescatalog.core.domains.movie.model.Movie;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface GetMovieByImdbIdUseCase {

    Movie execute(final String imdbId);
}
