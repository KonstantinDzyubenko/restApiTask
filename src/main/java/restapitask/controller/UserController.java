package restapitask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restapitask.dto.UserJsonDTO;
import restapitask.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/api/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USERS_VIEWER')")
    public List<UserJsonDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USERS_VIEWER')")
    public UserJsonDTO getUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @DeleteMapping("/api/users/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USERS_EDITOR')")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }

    @PostMapping("/api/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USERS_EDITOR')")
    public UserJsonDTO createUser(@RequestBody UserJsonDTO user) {
        return service.createUser(user);
    }

    @PutMapping("/api/users/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USERS_EDITOR')")
    public UserJsonDTO updateUser(@RequestBody UserJsonDTO user, @PathVariable int id) {
        return service.updateUser(user, id);
    }
}
