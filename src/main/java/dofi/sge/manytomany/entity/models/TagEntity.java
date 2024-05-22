package dofi.sge.manytomany.entity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags")
public class TagEntity extends AuditableEntity {

    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "tags")
    @JsonIgnore
    private Set<TutorialEntity> tutorials = new HashSet<>();

}
