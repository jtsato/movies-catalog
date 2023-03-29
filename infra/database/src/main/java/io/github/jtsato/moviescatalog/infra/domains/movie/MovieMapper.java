package io.github.jtsato.moviescatalog.infra.domains.movie;

import io.github.jtsato.moviescatalog.core.domains.movie.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Jorge Takeshi Sato
 */

@Mapper
public interface MovieMapper {

    Movie of(final MovieEntity movieEntity);
    @Mapping(target = "score", ignore = true)
    MovieEntity of(final Movie movie);
}
