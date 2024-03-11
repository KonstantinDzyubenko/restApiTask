package restapitask.management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import restapitask.configuration.security.Role;
import restapitask.management.controller.dto.UserDTO;
import restapitask.management.repository.RoleRepository;
import restapitask.management.repository.UserRepository;
import restapitask.management.repository.entity.RoleEntity;
import restapitask.management.repository.entity.UserEntity;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserManagementService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public void createUser(UserDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(encoder.encode(user.getPassword()));
        userEntity.setRoles(new ArrayList<>());
        for (Role role : user.getRoles()) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setUser(userEntity);
            roleEntity.setRole(role);
            userEntity.getRoles().add(roleEntity);
        }
        userRepository.save(userEntity);
        for (RoleEntity roleEntity : userEntity.getRoles()) {
            roleRepository.save(roleEntity);
        }
    }
}
