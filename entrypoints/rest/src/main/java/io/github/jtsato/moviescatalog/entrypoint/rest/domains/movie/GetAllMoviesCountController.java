package io.github.jtsato.moviescatalog.entrypoint.rest.domains.movie;

/*
 * A EntryPoint follows these steps:
 *
 * - Maps HTTP requests to Java objects
 * - Performs authorization checks
 * - Maps input to the input model of the use case
 * - Calls the use case
 * - Maps the output of the use case back to HTTP Returns an HTTP response
 */

import io.github.jtsato.moviescatalog.core.domains.movie.usecase.GetAllMoviesCountUseCase;
import io.github.jtsato.moviescatalog.entrypoint.rest.common.WebRequest;
import io.github.jtsato.moviescatalog.entrypoint.rest.common.metric.LogExecutionTime;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/movies/count")
public class GetAllMoviesCountController implements GetAllMoviesCountApiMethod {

    private static final Logger log = LoggerFactory.getLogger(GetAllMoviesCountController.class);

    private final WebRequest webRequest;
    private final GetAllMoviesCountUseCase getAllMoviesCountUseCase;

    @Override
    @LogExecutionTime
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public MoviesCountResponse execute() {
        log.info("Starting Controller -> GetAllMoviesCountController for Player {}", webRequest.getEmail());
        return new MoviesCountResponse(getAllMoviesCountUseCase.execute());
    }
}
