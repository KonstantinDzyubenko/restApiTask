package restapitask.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import restapitask.configuration.security.SecurityConfiguration;
import restapitask.service.AlbumService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    @WithAnonymousUser
    public void anonymousUserTest() throws Exception {
        mockMvc.perform(get("/api/albums")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"POSTS_VIEWER"})
    public void forbiddenTest() throws Exception {
        mockMvc.perform(get("/api/albums")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ALBUMS_VIEWER"})
    public void allowedTest() throws Exception {
        mockMvc.perform(get("/api/albums")).andExpect(status().isOk());
    }
}