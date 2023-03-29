package io.github.jtsato.moviescatalog.infra.domains.movie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Get All Movies Count Provider Test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import({GetAllMoviesCountProvider.class})
@Sql("GetAllMoviesCountProviderTest.sql")
class GetAllMoviesCountProviderTest {

    @Autowired
    private GetAllMoviesCountProvider getAllMoviesCountProvider;

    @Autowired
    private MovieRepository movieRepository;

    @DisplayName("Get All Movies Count")
    @Test
    void successfulToGetAllMoviesCount() {
        // Arrange
        // Act
        final Long actual = getAllMoviesCountProvider.execute();

        // Assert
        assertThat(actual).isEqualTo(2L);

        assertThat(movieRepository.count()).isEqualTo(2L);
    }
}
