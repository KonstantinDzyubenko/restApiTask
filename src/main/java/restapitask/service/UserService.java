package restapitask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import restapitask.client.JsonPlaceholderClient;
import restapitask.dto.UserJsonDTO;

@Component
@RequiredArgsConstructor
public class UserService {
    private final JsonPlaceholderClient client;

    public UserJsonDTO[] getAllUsers() {
        return client.getAllUsers();
    }

    public UserJsonDTO getUserById(int id) {
        return client.getUserById(id);
    }

    public void deleteUser(int id) {
        client.deleteUser(id);
    }

    public UserJsonDTO createUser(UserJsonDTO user) {
        return client.createUser(user);
    }

    public UserJsonDTO updateUser(UserJsonDTO user, int id) {
        client.updateUser(user, id);
        return user;
    }
}
