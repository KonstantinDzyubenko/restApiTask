package restapitask.management.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import restapitask.management.repository.entity.UserEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;

    @Test
    public void constraintViolationTest() {
        underTest.save(createUserEntity());
        assertThrows(DataIntegrityViolationException.class, () -> underTest.save(createUserEntity()));
    }

    private UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("constraintViolator");
        userEntity.setPassword("password");
        userEntity.setRoles(List.of());
        return userEntity;
    }
}