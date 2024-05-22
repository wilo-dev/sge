package dofi.sge.student.entity.request;

import dofi.sge.student.entity.model.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParaleloRequest {
    private String paralelo;
    private Boolean status;


}
