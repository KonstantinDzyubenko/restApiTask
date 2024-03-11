package restapitask.management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import restapitask.management.controller.dto.UserDTO;
import restapitask.management.service.UserManagementService;

@RestController
@RequiredArgsConstructor
public class ApplicationUserController {
    private final UserManagementService service;

    @PostMapping("/management/users")
    public void addUser(@RequestBody UserDTO user) {
        service.createUser(user);
    }
}
