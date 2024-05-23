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
    private String quimestre;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity studentId;

    @OneToMany(mappedBy = "quimestreId", cascade = CascadeType.ALL)
    private Set<ParcialEntity> parciales;

    @Column(name = "promedio_quimestral", nullable = false)
    private Double promedioQuimestral;

    public QuimestreEntity(QuimestreRequest data) {
        this.quimestre = data.getQuimestre();
        this.studentId = data.getStudent();
        this.parciales = data.getParciales();
        this.promedioQuimestral = calcularPromedioQuimestral();

    }

    private Double calcularPromedioQuimestral() {
        return parciales.stream()
                .mapToDouble(ParcialEntity::getPromedio)
                .average()
                .orElse(0.0);
    }

    public void updateDataQuimestre(QuimestreRequest data) {
        this.quimestre = data.getQuimestre();
        this.studentId = data.getStudent();
        this.parciales = data.getParciales();
        this.setUpdatedAt(new Date());
    }


    @Override
    public String toString() {
        return "QuimestreEntity{" +
                "nameQuimestre='" + quimestre + '\'' +
                ", studentId=" + studentId +
                ", parciales=" + parciales +
                '}';
    }
}
