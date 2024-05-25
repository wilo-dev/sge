package dofi.sge.student.entity.request;

import dofi.sge.student.entity.model.ParcialEntity;
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
    private Long parcialId;
//    private ParcialEntity parcialId;

}
