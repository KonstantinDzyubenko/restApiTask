package restapitask.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import restapitask.configuration.security.SecurityConfiguration;
import restapitask.service.AlbumService;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlbumController.class)
@Import(SecurityConfiguration.class)
class AlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AlbumService albumService;
    @MockBean
    private UserDetailsService userDetailsService;

    @ParameterizedTest
    @MethodSource("getTestParameters")
    @WithAnonymousUser
    public void anonymousUserTest(MockHttpServletRequestBuilder request) throws Exception {
        mockMvc.perform(request).andExpect(status().isUnauthorized());
    }

    @ParameterizedTest
    @MethodSource("getTestParameters")
    @WithMockUser(roles = {"POSTS_VIEWER"})
    public void forbiddenTest(MockHttpServletRequestBuilder request) throws Exception {
        mockMvc.perform(request).andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @MethodSource("getTestParameters")
    @WithMockUser(roles = {"ALBUMS_VIEWER", "ALBUMS_EDITOR"})
    public void allowedTest(MockHttpServletRequestBuilder request) throws Exception {
        mockMvc.perform(request).andExpect(status().isOk());
    }

    private static Stream<Arguments> getTestParameters() {
        return Stream.of(
                arguments(get("/api/albums")),
                arguments(get("/api/albums/1")),
                arguments(get("/api/users/1/albums")),
                arguments(delete("/api/albums/1")),
                arguments(post("/api/albums").content("{}").contentType("application/json")),
                arguments(put("/api/albums/1").content("{}").contentType("application/json"))
        );
    }
}