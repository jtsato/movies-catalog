package io.github.jtsato.moviescatalog.core.domains.movie.usecase;

import io.github.jtsato.moviescatalog.core.domains.movie.xcutting.GetAllMoviesCountGateway;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

/*
 * A Use Case follows these steps:
 * - Takes input
 * - Validates business rules
 * - Manipulates the model state
 * - Returns output
 */

/**
 * @author Jorge Takeshi Sato
 */

@Named
@RequiredArgsConstructor
public class GetAllMoviesCountUseCaseImpl implements GetAllMoviesCountUseCase {

    private final GetAllMoviesCountGateway getAllMoviesCountGateway;

    @Override
    public Long execute() {
        return getAllMoviesCountGateway.execute();
    }
}
