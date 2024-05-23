package dofi.sge.student.entity.model;

import dofi.sge.student.entity.request.StudentRequest;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class StudentEntity extends AuditableEntity {

    @Column(name = "first_name", nullable = false, length = 225)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 225)
    private String lastName;

    @Column(length = 225, unique = true)
    private String email;

    @Column(length = 50)
    private String code;

//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    private List<NotasEntity> notas;

    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL)
    private Set<QuimestreEntity> quimestres;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CoursesEntity courseId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "paralelo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ParaleloEntity paraleloId;

    //    request es lo que envio del boby de postman
    public StudentEntity(StudentRequest data) {
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
        this.email = data.getEmail();
        this.code = data.getCode();
        this.courseId = data.getCourseId();
        this.paraleloId = data.getParaleloId();
//        this.setStatus(true);
    }

    public void updateData(StudentRequest data) {
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
        this.email = data.getEmail();
        this.courseId = data.getCourseId();
        this.paraleloId = data.getParaleloId();
        this.setUpdatedAt(new Date());
    }

    public void updateDataStatus(boolean status) {
        this.setStatus(status);
        this.setUpdatedAt(new Date());
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
//TODO: entity datos q se guardan en la DB