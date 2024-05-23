package dofi.sge.student.entity.model;

import dofi.sge.student.entity.request.ParaleloRequest;
import dofi.sge.student.entity.request.ParcialRequest;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parciales")
public class ParcialEntity extends AuditableEntity {

    @Column(name = "name_parcial", nullable = false, length = 20)
    private String parcial;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "quimestre_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private QuimestreEntity quimestreId;

    @OneToMany(mappedBy = "parcialId", cascade = CascadeType.ALL)
    private Set<NotasEntity> notas;

    @Column(nullable = false)
    private Double promedio;

    public ParcialEntity(ParcialRequest data) {
        this.parcial = data.getNameParcial();
        this.quimestreId = data.getQuimestreId();
        this.promedio = gePromedioNotas();
//        setPromedio(gePromedioNotas());
    }

    private Double gePromedioNotas() {
        return notas.stream()
                .mapToDouble(NotasEntity::calcularPromedio)
                .average()
                .orElse(0.0);
    }

    public void updateDataParcial(ParcialRequest data) {
        this.parcial = data.getNameParcial();
        this.quimestreId = data.getQuimestreId();
        this.setUpdatedAt(new Date());
    }

    @Override
    public String toString() {
        return "ParcialEntity{" +
                "parcial='" + parcial + '\'' +
                ", quimestreId=" + quimestreId +
                ", notas=" + notas +
                '}';
    }
}
