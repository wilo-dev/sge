package dofi.sge.student.entity.model;

import dofi.sge.student.entity.request.QuimestreRequest;
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
@Table(name = "quimestres")
public class QuimestreEntity extends AuditableEntity {

    @Column(name = "nombre_quimestre", nullable = false, length = 20)
    private String quimestre;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity studentId;

    @OneToMany(mappedBy = "quimestreId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParcialEntity> parciales = new HashSet<>();

    @Column(name = "promedio_quimestral")
    private Double promedioQuimestral;

    public QuimestreEntity(QuimestreRequest data, StudentEntity studentId) {
        this.quimestre = data.getQuimestre();
        this.studentId = studentId;
//        this.studentId = data.getStudentId();
        if (data.getParciales() != null) {
            this.parciales = data.getParciales();
            this.promedioQuimestral = calcularPromedioQuimestral();
        }
    }

    public void updateDataQuimestre(QuimestreRequest data, StudentEntity studentId) {
        this.quimestre = data.getQuimestre();
        this.studentId = studentId;
//        this.studentId = data.getStudentId();
        if (data.getParciales() != null) {
            this.parciales = data.getParciales();
            this.promedioQuimestral = calcularPromedioQuimestral();
        }
        this.promedioQuimestral = calcularPromedioQuimestral();
        this.setUpdatedAt(new Date());
    }

    @Transient
    public Double calcularPromedioQuimestral() {
        return parciales.stream()
                .mapToDouble(ParcialEntity::calcularPromedioParcial)
                .average()
                .orElse(0.0);
    }

//    public void setParciales(Set<ParcialEntity> parciales) {
//        this.parciales = parciales;
//        this.promedioQuimestral = calcularPromedioQuimestral();
//    }

    @Override
    public String toString() {
        return "QuimestreEntity{" +
                "nameQuimestre='" + quimestre + '\'' +
                ", studentId=" + studentId +
                ", parciales=" + parciales +
                '}';
    }
}
