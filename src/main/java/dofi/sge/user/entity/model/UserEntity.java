package dofi.sge.user.entity.model;

import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends AuditableEntity {
    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String LastName;

    @Column(nullable = false, length = 50)
    private String nick;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<RolesEntity> roles;
}
