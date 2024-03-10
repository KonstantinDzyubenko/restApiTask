package p1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import p1.dto.UserJsonDTO;
import p1.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/api/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USERS_VIEWER')")
    public UserJsonDTO[] getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USERS_VIEWER')")
    public UserJsonDTO getUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @DeleteMapping("/api/users/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USERS')")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }

    @PostMapping("/api/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USERS')")
    public UserJsonDTO createUser(@RequestBody UserJsonDTO user) {
        return service.createUser(user);
    }

    @PutMapping("/api/users/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USERS')")
    public UserJsonDTO updateUser(@RequestBody UserJsonDTO user, @PathVariable int id) {
        return service.updateUser(user, id);
    }
}
