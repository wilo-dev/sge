package dofi.sge.student.entity.model;

import dofi.sge.student.entity.request.ParcialRequest;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parciales")
public class ParcialEntity extends AuditableEntity {

    @Column(name = "name_parcial", length = 20)
    private String parcial;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "quimestre_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private QuimestreEntity quimestreId;

    @OneToMany(mappedBy = "parcialId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NotasEntity> notas = new HashSet<>();

    @Column(name = "promedio_parcial")
    private Double promedioParcial;

    public ParcialEntity(ParcialRequest data, QuimestreEntity quimestreId) {
        this.parcial = data.getNameParcial();
        this.quimestreId = quimestreId;
        if (data.getNotas() != null) {
            this.notas = data.getNotas();
            this.promedioParcial = calcularPromedioParcial();
        }
    }

    public void updateDataParcial(ParcialRequest data) {
        this.parcial = data.getNameParcial();
        this.quimestreId = quimestreId;
        if (data.getNotas() != null) {
            this.notas = data.getNotas();
            this.promedioParcial = calcularPromedioParcial();
        }
        this.setUpdatedAt(new Date());
    }

    @Transient
    public Double calcularPromedioParcial() {
        double resp = notas.stream()
                .mapToDouble(NotasEntity::getPromedio)
                .average()
                .orElse(0.0);
        log.info(String.valueOf(resp));
        return resp;
    }

//    public void setNotas(Set<NotasEntity> notas) {
//        this.notas = notas;
//        this.promedioParcial = calcularPromedioParcial();
//    }

    @Override
    public String toString() {
        return "ParcialEntity{" +
                "parcial='" + parcial + '\'' +
                ", quimestreId=" + quimestreId +
                ", notas=" + notas +
                ", promedioParcial=" + promedioParcial +
                '}';
    }
}
