package io.github.jtsato.moviescatalog.core.domains.movie.usecase;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface GetAllMoviesCountUseCase {

    Long execute();
}
