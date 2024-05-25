package dofi.sge.student.entity.request;

import dofi.sge.student.entity.model.NotasEntity;
import dofi.sge.student.entity.model.QuimestreEntity;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcialRequest {

    private String nameParcial;
    private Boolean status;
    private QuimestreEntity quimestreId;
    private Set<NotasEntity> notas = new HashSet<>();


}
