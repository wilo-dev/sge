package dofi.sge.student.entity.request;

import dofi.sge.student.entity.model.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private String course;
    private Boolean status;
    
//    private List<StudentEntity> students;
}
