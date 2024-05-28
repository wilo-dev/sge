package dofi.sge.student.entity.model;

import dofi.sge.student.entity.request.QuimestreRequest;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_quimestre_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private QuimestreItemEntity itemQuimestreId;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StudentEntity studentId;

    @OneToMany(mappedBy = "quimestreId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParcialEntity> parciales = new HashSet<>();

    @Column(name = "promedio_quimestral")
    private Double promedioQuimestral;

    public QuimestreEntity(QuimestreRequest data, StudentEntity studentId, QuimestreItemEntity itemQuimestreId) {
        this.itemQuimestreId = itemQuimestreId;
        this.studentId = studentId;
        if (data.getParciales() != null) {
            this.parciales = data.getParciales();
            this.promedioQuimestral = calcularPromedioQuimestral();
        }
    }

    public void updateDataQuimestre(QuimestreRequest data, StudentEntity studentId, QuimestreItemEntity itemQuimestreId) {
        this.itemQuimestreId = itemQuimestreId;
        this.studentId = studentId;
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

    @Override
    public String toString() {
        return "QuimestreEntity{" +
                "itemQuimestreId=" + itemQuimestreId +
                ", studentId=" + studentId +
                ", parciales=" + parciales +
                ", promedioQuimestral=" + promedioQuimestral +
                '}';
    }

}
