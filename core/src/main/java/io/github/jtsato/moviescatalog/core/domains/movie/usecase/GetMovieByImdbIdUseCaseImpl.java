package io.github.jtsato.moviescatalog.core.domains.movie.usecase;

import io.github.jtsato.moviescatalog.core.domains.movie.model.Movie;
import io.github.jtsato.moviescatalog.core.domains.movie.xcutting.GetMovieByImdbIdGateway;
import io.github.jtsato.moviescatalog.core.exception.NotFoundException;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@Named
@RequiredArgsConstructor
public class GetMovieByImdbIdUseCaseImpl implements GetMovieByImdbIdUseCase {

    private final GetMovieByImdbIdGateway getMovieByImdbIdGateway;

    @Override
    public Movie execute(final String imdbId) {
        Optional<Movie> optional = getMovieByImdbIdGateway.execute(imdbId);
        return optional.orElseThrow(() -> new NotFoundException("validation.movie.notfound", imdbId));
    }
}
