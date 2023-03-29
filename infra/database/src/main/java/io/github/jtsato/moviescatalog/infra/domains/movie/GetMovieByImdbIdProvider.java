package io.github.jtsato.moviescatalog.infra.domains.movie;

import io.github.jtsato.moviescatalog.core.domains.movie.model.Movie;
import io.github.jtsato.moviescatalog.core.domains.movie.xcutting.GetMovieByImdbIdGateway;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetMovieByImdbIdProvider implements GetMovieByImdbIdGateway {

    private final MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);
    
    private final MovieRepository movieRepository;

    @Override
    public Optional<Movie> execute(String imdbId) {
        final Optional<MovieEntity> optional = movieRepository.findByImdbIdIgnoreCase(imdbId);
        return optional.map(movieMapper::of);
    }
}
