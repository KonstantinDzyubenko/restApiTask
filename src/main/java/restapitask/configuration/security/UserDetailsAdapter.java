package restapitask.configuration.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import restapitask.management.repository.entity.UserEntity;

public class UserDetailsAdapter {
    public static UserDetails userToDetails(UserEntity userEntity) {
        return new User(
                        userEntity.getLogin(),
                        userEntity.getPassword(),
                        userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole())).toList()
                );
    }
}
