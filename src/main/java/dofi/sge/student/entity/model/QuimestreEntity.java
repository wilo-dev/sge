package dofi.sge.student.entity.model;

import dofi.sge.student.entity.request.QuimestreRequest;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quimestres")
public class QuimestreEntity extends AuditableEntity {

    @Column(name = "nombre_quimestre", nullable = false, length = 20)
    private String nameQuimestre;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @OneToMany(mappedBy = "quimestre", cascade = CascadeType.ALL)
    private Set<ParcialEntity> parciales;

    public QuimestreEntity(QuimestreRequest data) {
        this.nameQuimestre = data.getNameQuimestre();
        this.student = data.getStudent();
        this.parciales = data.getParciales();
    }

    public void updateDataQuimestre(QuimestreRequest data) {
        this.nameQuimestre = data.getNameQuimestre();
        this.student = data.getStudent();
        this.parciales = data.getParciales();
        this.setUpdatedAt(new Date());
    }

    @Override
    public String toString() {
        return "QuimestreEntity{" +
                "nameQuimestre='" + nameQuimestre + '\'' +
                ", student=" + student +
                ", parciales=" + parciales +
                '}';
    }
}
