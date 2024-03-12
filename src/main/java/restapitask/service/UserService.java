package restapitask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import restapitask.client.JsonPlaceholderClient;
import restapitask.dto.UserJsonDTO;

@Component
@RequiredArgsConstructor
public class UserService {
    private final JsonPlaceholderClient client;

    @Cacheable(value = "allUsers")
    public UserJsonDTO[] getAllUsers() {
        return client.getAllUsers();
    }

    @Cacheable(value = "users", key = "#id")
    public UserJsonDTO getUserById(int id) {
        return client.getUserById(id);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(int id) {
        client.deleteUser(id);
    }

    @CachePut(value = "users", key = "#result.id")
    public UserJsonDTO createUser(UserJsonDTO user) {
        return client.createUser(user);
    }

    @CachePut(value = "users", key = "#id")
    public UserJsonDTO updateUser(UserJsonDTO user, int id) {
        client.updateUser(user, id);
        return user;
    }
}
