package restapitask.management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import restapitask.management.controller.dto.UserDTO;
import restapitask.management.service.UserManagementService;

@RestController
@RequiredArgsConstructor
public class UserManagementController {
    private final UserManagementService service;

    @PostMapping("/management/users")
    @PreAuthorize("hasAuthority('ROLE_APPLICATION_ADMIN')")
    public void addUser(@RequestBody @Validated UserDTO user) {
        service.createUser(user);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionHandler(Exception e) {
        return e.getMessage();
    }
}
