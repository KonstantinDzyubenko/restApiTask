package restapitask.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import restapitask.client.JsonPlaceholderClient;
import restapitask.dto.UserJsonDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserService.class)
@ContextConfiguration(classes = UserServiceTest.Config.class)
class UserServiceTest {
    @MockBean
    private JsonPlaceholderClient client;
    @Autowired
    private UserService underTest;
    @Autowired
    private CacheManager cacheManager;

    @AfterEach
    public void afterEach() {
        cacheManager.getCacheNames().forEach(name -> cacheManager.getCache(name).clear());
    }

    @Test
    public void getAllUsersTest() {
        when(client.getAllUsers()).thenReturn(createUsers());
        List<UserJsonDTO> result = underTest.getAllUsers();
        assertEquals(createUsers().get(0), result.get(0));
        result = underTest.getAllUsers();
        verify(client, times(1)).getAllUsers();
        assertEquals(createUsers().get(0), result.get(0));
    }

    @Test
    public void getUserByIdTest() {
        when(client.getUserById(eq(1))).thenReturn(createUser());
        UserJsonDTO result = underTest.getUserById(1);
        assertEquals(createUser(), result);
        result = underTest.getUserById(1);
        verify(client, times(1)).getUserById(eq(1));
        assertEquals(createUser(), result);
    }

    @Test
    public void createUserTest() {
        when(client.createUser(any())).thenReturn(createUser());
        assertEquals(createUser(), underTest.createUser(createUser()));
        underTest.getUserById(1);
        verify(client, never()).getUserById(eq(1));
    }

    @Test
    public void deleteUserTest() {
        when(client.createUser(any())).thenReturn(createUser());
        underTest.createUser(createUser());
        underTest.deleteUser(1);
        underTest.getUserById(1);
        verify(client, times(1)).getUserById(eq(1));
    }

    @Test
    public void updateUserTest() {
        when(client.createUser(any())).thenReturn(createUser());
        UserJsonDTO updatedUser = createUser();
        updatedUser.setName("updated");
        when(client.updateUser(any(), eq(1))).thenReturn(updatedUser);
        underTest.updateUser(updatedUser, 1);
        assertEquals(updatedUser, underTest.getUserById(1));
        verify(client, never()).getUserById(eq(1));
    }

    @Configuration
    @EnableCaching
    public static class Config {
        @Bean
        public CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("users", "allUsers");
        }
    }

    private List<UserJsonDTO> createUsers() {
        return List.of(new UserJsonDTO());
    }

    private UserJsonDTO createUser() {
        UserJsonDTO result = new UserJsonDTO();
        result.setId(1);
        return result;
    }
}