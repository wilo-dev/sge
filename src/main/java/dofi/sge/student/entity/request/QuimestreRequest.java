package dofi.sge.student.entity.request;

import dofi.sge.student.entity.model.ParcialEntity;
import dofi.sge.student.entity.model.StudentEntity;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuimestreRequest {

    private String nameQuimestre;
    private StudentEntity student;
    private Boolean status;
    private Set<ParcialEntity> parciales;


}
