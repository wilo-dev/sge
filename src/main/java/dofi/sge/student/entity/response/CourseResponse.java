package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.CoursesEntity;
import lombok.Data;

@Data
public class CourseResponse {
    private Long id;
    private String course;

    public CourseResponse(CoursesEntity resp) {
        this.id = resp.getId();
        this.course = resp.getCourse();
    }
}

