package dofi.sge.student.entity.model;

import dofi.sge.student.entity.request.ParcialItemRequest;
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
@Table(name = "items_parciales")
public class ParcialItemEntity extends AuditableEntity {

    @Column(name = "nombre_parcial", nullable = false, length = 20)
    private String nameParcial;

    @OneToMany(mappedBy = "itemParcialId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParcialEntity> parcial = new HashSet<>();

    public ParcialItemEntity(ParcialItemRequest data) {
        this.nameParcial = data.getItemParcial();
    }

    public void updateQuimestreItem(ParcialItemRequest data) {
        this.nameParcial = data.getItemParcial();
        this.setUpdatedAt(new Date());
    }
}
