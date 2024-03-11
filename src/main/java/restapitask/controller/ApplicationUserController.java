package restapitask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restapitask.repository.RoleRepository;
import restapitask.repository.UserRepository;
import restapitask.repository.dto.Role;
import restapitask.repository.dto.User;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class ApplicationUserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostMapping("/createUser")
    public void addUser(@RequestParam String login, @RequestParam String password, @RequestParam String... roles) {
        User user = new User();
        user.setLogin(login);
        PasswordEncoder encoder = new BCryptPasswordEncoder(5);
        user.setPassword(encoder.encode(password));
        user.setRoles(new ArrayList<>());
        for (String role : roles) {
            Role roleDTO = new Role();
            roleDTO.setUser(user);
            roleDTO.setRole(role);
            roleRepository.save(roleDTO);
            user.getRoles().add(roleDTO);
        }
        userRepository.save(user);
    }
}
