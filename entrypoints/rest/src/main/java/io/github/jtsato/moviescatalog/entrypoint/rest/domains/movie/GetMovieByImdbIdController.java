package io.github.jtsato.moviescatalog.entrypoint.rest.domains.movie;

import io.github.jtsato.moviescatalog.core.domains.movie.model.Movie;
import io.github.jtsato.moviescatalog.core.domains.movie.usecase.GetMovieByImdbIdUseCase;
import io.github.jtsato.moviescatalog.entrypoint.rest.common.WebRequest;
import io.github.jtsato.moviescatalog.entrypoint.rest.common.metric.LogExecutionTime;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/movies/byImdbId/{imdbId}")
public class GetMovieByImdbIdController implements GetMovieByImdbIdApiMethod {

    private static final Logger log = LoggerFactory.getLogger(GetMovieByImdbIdController.class);

    private final WebRequest webRequest;
    private final GetMovieByImdbIdUseCase getMovieByImdbIdUseCase;

    @Override
    @LogExecutionTime
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public MovieResponse execute(@PathVariable String imdbId) {
        log.info("Starting Controller -> GetMovieByImdbIdController for Player {}", webRequest.getEmail());
        final Movie movie = getMovieByImdbIdUseCase.execute(imdbId);
        return MoviePresenter.of(movie);
    }
}
