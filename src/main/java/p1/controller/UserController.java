package p1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import p1.dto.UserJsonDTO;
import p1.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/api/users")
    public UserJsonDTO[] getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public UserJsonDTO getUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }

    @PostMapping("/api/users")
    public UserJsonDTO createUser(@RequestBody UserJsonDTO user) {
        return service.createUser(user);
    }

    @PutMapping("/api/users/{id}")
    public UserJsonDTO updateUser(@RequestBody UserJsonDTO user, @PathVariable int id) {
        return service.updateUser(user, id);
    }
}
