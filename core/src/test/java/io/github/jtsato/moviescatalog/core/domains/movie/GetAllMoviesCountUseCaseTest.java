package io.github.jtsato.moviescatalog.core.domains.movie;

import io.github.jtsato.moviescatalog.core.domains.movie.usecase.GetAllMoviesCountUseCase;
import io.github.jtsato.moviescatalog.core.domains.movie.usecase.GetAllMoviesCountUseCaseImpl;
import io.github.jtsato.moviescatalog.core.domains.movie.xcutting.GetAllMoviesCountGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Get all movies count Use Case Test")
class GetAllMoviesCountUseCaseTest {

    @Mock private final GetAllMoviesCountGateway getAllMoviesCountGateway = Mockito.mock(GetAllMoviesCountGateway.class);

    @InjectMocks
    private final GetAllMoviesCountUseCase getAllMoviesCountUseCase = new GetAllMoviesCountUseCaseImpl(getAllMoviesCountGateway);

    @DisplayName("Fail to Get all movies count with invalid action")
    @Test
    void failToGetAllMoviesCountWithInvalidAction() {
        // Arrange
        when(getAllMoviesCountGateway.execute()).thenReturn(1L);

        // Act
        final Long count = getAllMoviesCountUseCase.execute();

        // Assert
        assertThat(count).isEqualTo(1L);
    }
}
