package io.github.jtsato.moviescatalog.core.domains.movie.xcutting;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface GetAllMoviesCountGateway {

    Long execute();
}
