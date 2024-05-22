package dofi.sge.student.entity.model;

import dofi.sge.student.entity.request.ParaleloRequest;
import dofi.sge.student.entity.request.ParcialRequest;
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
@Table(name = "parciales")
public class ParcialEntity extends AuditableEntity {

    @Column(name = "name_parcial", nullable = false, length = 20)
    private String nameParcial;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "quimestre_id")
    private QuimestreEntity quimestre;

    @OneToMany(mappedBy = "parcial")
    private Set<NotasEntity> notas;

    public ParcialEntity(ParcialRequest data) {
        this.nameParcial = data.getNameParcial();
        this.quimestre = data.getQuimestre();
    }

    public void updateDataParcial(ParcialRequest data) {
        this.nameParcial = data.getNameParcial();
        this.quimestre = data.getQuimestre();
        this.setUpdatedAt(new Date());
    }

    @Override
    public String toString() {
        return "ParcialEntity{" +
                "nameParcial='" + nameParcial + '\'' +
                ", quimestre=" + quimestre +
                ", notas=" + notas +
                '}';
    }
}
