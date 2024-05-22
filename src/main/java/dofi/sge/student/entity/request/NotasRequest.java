package dofi.sge.student.entity.request;

import dofi.sge.student.entity.model.ParcialEntity;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotasRequest {

    private double noteTasks;
    private double noteGroupWorks;
    private double noteLessons;
    private double exam;
    private Boolean status;

}
