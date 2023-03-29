package io.github.jtsato.moviescatalog.entrypoint.rest.domains.movie;

import io.github.jtsato.moviescatalog.core.domains.movie.usecase.GetAllMoviesCountUseCase;
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

@DisplayName("Get all movies count Controller Test")
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GetAllMoviesCountController.class)
class GetAllMoviesCountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllMoviesCountUseCase getAllMoviesCountUseCase;

    @MockBean
    private WebRequest webRequest;

    @DisplayName("Successful to Get all movies count")
    @Test
    void SuccessfulToGetAllMoviesCount() throws Exception {
        // Arrange
        when(webRequest.getEmail()).thenReturn("joe.doe.one@xyz.com");
        when(webRequest.getFullName()).thenReturn("Joe Doe");

        when(getAllMoviesCountUseCase.execute()).thenReturn(1L);

        // Act
        // Assert
        mockMvc.perform(get("/v1/movies/count").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.count", is(1)));

        verify(getAllMoviesCountUseCase, times(1)).execute();
        verifyNoMoreInteractions(getAllMoviesCountUseCase);
    }
}
