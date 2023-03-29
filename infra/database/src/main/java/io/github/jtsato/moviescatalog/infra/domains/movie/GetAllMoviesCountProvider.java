package io.github.jtsato.moviescatalog.infra.domains.movie;

import io.github.jtsato.moviescatalog.core.domains.movie.xcutting.GetAllMoviesCountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetAllMoviesCountProvider implements GetAllMoviesCountGateway {

    private final MovieRepository movieRepository;

    @Override
    public Long execute() {
        return movieRepository.count();
    }
}