package dofi.sge.student.entity.request;

import dofi.sge.student.entity.model.CoursesEntity;
import dofi.sge.student.entity.model.NotasEntity;
import dofi.sge.student.entity.model.ParaleloEntity;
import dofi.sge.student.entity.model.QuimestreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String firstName;

    private String LastName;

    private String email;

    private String code;

    private Boolean status;

    private Set<QuimestreEntity> quimestres;

    private Long courseId;

    private Long paraleloId;

    private Double promedioAnual;
}

//TODO: request lo q se envia por el body de postman