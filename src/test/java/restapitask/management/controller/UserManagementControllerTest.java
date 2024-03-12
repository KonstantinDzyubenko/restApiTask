package restapitask.management.controller;

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
import restapitask.management.service.UserManagementService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserManagementController.class)
@Import(SecurityConfiguration.class)
class UserManagementControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserManagementService userManagementService;
    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    @WithAnonymousUser
    public void anonymousUserTest() throws Exception {
        mockMvc.perform(post("/management/users")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"POSTS_VIEWER"})
    public void forbiddenTest() throws Exception {
        mockMvc.perform(post("/management/users")
                .content(REQUEST_BODY)
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = {"APPLICATION_ADMIN"})
    public void allowedTest() throws Exception {
        mockMvc.perform(post("/management/users")
                .content(REQUEST_BODY)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"APPLICATION_ADMIN"})
    public void validationFailureTest() throws Exception {
        mockMvc.perform(post("/management/users")
                        .content(BAD_REQUEST_BODY)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    private static final String REQUEST_BODY = """
            {
                "login": "user3",
                "password": "password3",
                "roles": ["ROLE_ADMIN"]
            }""";
    private static final String BAD_REQUEST_BODY = """
            {
                "login": "",
                "password": "",
                "roles": []
            }""";
}