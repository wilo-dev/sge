package dofi.sge.student.entity.model;

import dofi.sge.student.entity.request.QuimestreItemRequest;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items_quimestre")
public class QuimestreItemEntity extends AuditableEntity {

    @Column(name = "nombre_quimestre", nullable = false, length = 10)
    private String nameQuimestre;

    @OneToMany(mappedBy = "itemQuimestreId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuimestreEntity> quimestre = new HashSet<>();

    public QuimestreItemEntity(QuimestreItemRequest data) {
        this.nameQuimestre = data.getItemQuimestre();
    }

    public void updateDataQuimestreItem(QuimestreItemRequest data) {
        this.nameQuimestre = data.getItemQuimestre();
        this.setUpdatedAt(new Date());
    }

    @Override
    public String toString() {
        return "QuimestreItemEntity{" +
                "nameQuimestre='" + nameQuimestre + '\'' +
                ", quimestre=" + quimestre +
                '}';
    }
}
