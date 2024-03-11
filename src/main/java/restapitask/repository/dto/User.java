package restapitask.repository.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Role> roles;
}
