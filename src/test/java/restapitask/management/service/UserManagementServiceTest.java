package restapitask.management.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import restapitask.management.controller.dto.UserDTO;
import restapitask.management.repository.RoleRepository;
import restapitask.management.repository.UserRepository;
import restapitask.management.repository.entity.RoleEntity;
import restapitask.management.repository.entity.UserEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static restapitask.configuration.security.Role.ROLE_ADMIN;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserManagementServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserManagementService underTest;

    @Test
    public void successfulUserCreationTest() {
        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        ArgumentCaptor<RoleEntity> roleCaptor = ArgumentCaptor.forClass(RoleEntity.class);
        when(encoder.encode(any())).thenReturn("12345");
        underTest.createUser(createUserDTO());
        verify(encoder).encode(eq("password"));
        verify(userRepository).save(userCaptor.capture());
        UserEntity capturedUser = userCaptor.getValue();
        assertEquals("login", capturedUser.getLogin());
        assertEquals("12345", capturedUser.getPassword());
        assertEquals(1, capturedUser.getRoles().size());
        assertEquals(ROLE_ADMIN, capturedUser.getRoles().get(0).getRole());
        verify(roleRepository).save(roleCaptor.capture());
        RoleEntity capturedRole = roleCaptor.getValue();
        assertEquals(ROLE_ADMIN, capturedRole.getRole());
    }

    private UserDTO createUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("login");
        userDTO.setPassword("password");
        userDTO.setRoles(List.of(ROLE_ADMIN));
        return userDTO;
    }
}