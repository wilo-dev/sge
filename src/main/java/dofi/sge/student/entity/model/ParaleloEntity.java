package dofi.sge.student.entity.model;

import dofi.sge.student.entity.request.ParaleloRequest;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paralelos")
public class ParaleloEntity extends AuditableEntity {

    @Column(nullable = false, length = 10)
    private String paralelo;

    @OneToMany(mappedBy = "paraleloId", cascade = CascadeType.ALL)
    private List<StudentEntity> students;

    public ParaleloEntity(ParaleloRequest data) {
        this.paralelo = data.getParalelo();
    }

    public void updateDateParalelo(ParaleloRequest data) {
        this.paralelo = data.getParalelo();
        this.setUpdatedAt(new Date());

    }
}
