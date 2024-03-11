package restapitask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restapitask.repository.UserRepository;
import restapitask.repository.dto.User;

@RestController
@RequiredArgsConstructor
public class ApplicationUserController {
    private final UserRepository userRepository;

    @PostMapping("/createUser")
    public void addUser(@RequestParam String login, @RequestParam String password, @RequestParam String role) {
        User user = new User();
        user.setLogin(login);
        PasswordEncoder encoder = new BCryptPasswordEncoder(5);
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
    }
}
