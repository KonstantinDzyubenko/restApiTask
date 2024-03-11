package restapitask.management.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restapitask.management.repository.entity.RoleEntity;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    List<RoleEntity> findByUserId(int id);
}