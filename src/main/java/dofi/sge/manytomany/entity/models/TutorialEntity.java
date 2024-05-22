package dofi.sge.manytomany.entity.models;

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
@Table(name = "tutorials")
public class TutorialEntity extends AuditableEntity {

    private String title;
    private String description;
    private String published;
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(name = "tutorial_tags",
            joinColumns = {@JoinColumn(name = "tutorial_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<TagEntity> tags = new HashSet<>();

    public void addTag(TagEntity tag) {
        this.tags.add(tag);
        tag.getTutorials().add(this);
    }

    public void removeTag(long tagId) {
        TagEntity tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getTutorials().remove(this);
        }
    }
}
