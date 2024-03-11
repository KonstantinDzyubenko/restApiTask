package restapitask.management.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import restapitask.configuration.security.Role;

@Data
@Entity
@Table(name = "tb_roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}

