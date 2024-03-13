package restapitask.configuration.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import restapitask.management.repository.UserRepository;
import restapitask.management.repository.entity.RoleEntity;
import restapitask.management.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static restapitask.configuration.security.Role.ROLE_ADMIN;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService underTest;

    @Test
    public void successfulSearchTest() {
        when(userRepository.findByLogin(eq("login"))).thenReturn(Optional.of(createUserEntity()));
        UserDetails userDetails = underTest.loadUserByUsername("login");
        assertEquals(createUserDetails(), userDetails);
    }

    @Test
    public void userNotFoundTest() {
        when(userRepository.findByLogin(eq("login"))).thenReturn(Optional.empty());
        UsernameNotFoundException e = assertThrows(UsernameNotFoundException.class, () -> underTest.loadUserByUsername("login"));
        assertEquals("user login not found", e.getMessage());
    }

    private UserEntity createUserEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1);
        roleEntity.setRole(ROLE_ADMIN);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setLogin("login");
        userEntity.setPassword("password");
        userEntity.setRoles(List.of(roleEntity));
        roleEntity.setUser(userEntity);
        return userEntity;
    }

    private UserDetails createUserDetails() {
        return new User("login", "password", List.of(new SimpleGrantedAuthority(ROLE_ADMIN.name())));
    }
}