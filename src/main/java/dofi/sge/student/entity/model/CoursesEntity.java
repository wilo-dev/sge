package dofi.sge.student.entity.model;


import dofi.sge.student.entity.request.CourseRequest;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "courses")
public class CoursesEntity extends AuditableEntity {

    @Column(nullable = false, length = 50)
    private String course;

    @OneToMany(mappedBy = "courseId", cascade = CascadeType.ALL)
    private Set<StudentEntity> students;

    public CoursesEntity(CourseRequest data) {
        this.course = data.getCourse();
    }

    public void updateDateCourse(CourseRequest data) {
        this.course = data.getCourse();
        this.setUpdatedAt(new Date());
    }

    @Override
    public String toString() {
        return "CoursesEntity{" +
                "course='" + course + '\'' +
                '}';
    }
}
