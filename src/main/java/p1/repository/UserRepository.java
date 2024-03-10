package p1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import p1.repository.dto.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
