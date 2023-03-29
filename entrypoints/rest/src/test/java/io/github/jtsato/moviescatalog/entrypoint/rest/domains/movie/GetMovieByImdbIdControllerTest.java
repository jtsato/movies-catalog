package io.github.jtsato.moviescatalog.entrypoint.rest.domains.movie;

import io.github.jtsato.moviescatalog.core.domains.movie.model.Movie;
import io.github.jtsato.moviescatalog.core.domains.movie.usecase.GetMovieByImdbIdUseCase;
import io.github.jtsato.moviescatalog.entrypoint.rest.common.WebRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Get Movie By ImdbId")
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GetMovieByImdbIdController.class)
class GetMovieByImdbIdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetMovieByImdbIdUseCase getMovieByImdbIdUseCase;

    @MockBean
    private WebRequest webRequest;

    @DisplayName("Successful to get movies by imdbId")
    @Test
    void successfulToGetMoviesByImdbId() throws Exception {
        // Arrange
        when(webRequest.getEmail()).thenReturn("joe.doe.one@xyz.com");
        when(webRequest.getFullName()).thenReturn("Joe Doe");

        when(getMovieByImdbIdUseCase.execute("tt0468569"))
                .thenReturn(buildDarkKnightMovie());

        // Act
        // Assert
        mockMvc.perform(get("/v1/movies/byImdbId/{imdbId}", "tt0468569")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.imdbId", is("tt0468569")))
                .andExpect(jsonPath("$.title", is("The Dark Knight")))
                .andExpect(jsonPath("$.year", is("2008")))
                .andExpect(jsonPath("$.genre", is("Action")))
                .andExpect(jsonPath("$.imdbRating", is(9.0)))
                .andExpect(jsonPath("$.imdbVotes", is(2628154)))
                .andExpect(jsonPath("$.score", is(23653386.0)))
                .andExpect(jsonPath("$.posterUrl", is("http://poster.url")));

        verify(getMovieByImdbIdUseCase, times(1)).execute("tt0468569");
        verifyNoMoreInteractions(getMovieByImdbIdUseCase);
    }

    private Movie buildDarkKnightMovie() {
        return new Movie(1L, "tt0468569", "The Dark Knight", "2008", "Action", 9F, 2628154L, 23653386F, "http://poster.url");
    }
}
