package io.github.jtsato.moviescatalog.infra.domains.movie;

import io.github.jtsato.moviescatalog.core.domains.movie.model.Movie;
import io.github.jtsato.moviescatalog.core.domains.movie.xcutting.GetRandomMovieGateway;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetRandomMovieProvider implements GetRandomMovieGateway {

    private final MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);
    private final MovieRepository movieRepository;

    @Override
    public Optional<Movie> execute(int index) {
        final PageRequest pageRequest = PageRequest.of(index, 1);
        final Optional<MovieEntity> optional = movieRepository.findAll(pageRequest).stream().findFirst();
        return optional.map(movieMapper::of);
    }
}
