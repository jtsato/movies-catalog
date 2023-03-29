package io.github.jtsato.moviescatalog.infra.domains.movie;

import io.github.jtsato.moviescatalog.core.domains.movie.model.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Get Movie By ImdbId Provider Test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import({GetMovieByImdbIdProvider.class})
@Sql("GetMovieByImdbIdProviderTest.sql")
class GetMovieByImdbIdProviderTest {

    @Autowired
    private GetMovieByImdbIdProvider getMovieByImdbIdProvider;

    @Autowired
    private MovieRepository movieRepository;

    @DisplayName("Fail to get movie by imdbId when imdbId does not exist")
    @Test
    void failToGetMovieByImdbIdWhenImdbIdDoesNotExist() {
        // Arrange
        // Act
        final Optional<Movie> optional = getMovieByImdbIdProvider.execute("tt0468590");

        // Assert
        assertThat(optional).isNotPresent();
    }

    @DisplayName("Get Movie By ImdbId")
    @Test
    void successfulToGetMovieByImdbId() {
        // Arrange
        // Act
        final Optional<Movie> optional = getMovieByImdbIdProvider.execute("tt0468569");

        // Assert
        assertThat(optional).isPresent();

        final Movie movie = optional.get();

        assertThat(movie).isNotNull();

        assertThat(movie.id()).isEqualTo(2L);
        assertThat(movie.imdbId()).isEqualTo("tt0468569");
        assertThat(movie.title()).isEqualTo("The Dark Knight");
        assertThat(movie.year()).isEqualTo("2008");
        assertThat(movie.genre()).isEqualTo("Action, Crime, Drama");
        assertThat(movie.imdbRating()).isEqualTo(9.0F);
        assertThat(movie.imdbVotes()).isEqualTo(2628154L);
        assertThat(movie.score()).isEqualTo(23653386L);
        assertThat(movie.posterUrl()).isEqualTo("https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg");

        assertThat(movieRepository.count()).isEqualTo(2L);
    }
}
