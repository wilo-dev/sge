package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.QuimestreEntity;
import dofi.sge.student.entity.model.StudentEntity;
import lombok.Data;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class StudentResponse {

    private Long id;
    private String firstName;
    private String LastName;
    private String email;
    private String code;
    private String course;
    private String paralelo;
    private Boolean status;
    private Set<QuimestreResponse> quimestres;
//    private List<NotasResponse> notas;

    public StudentResponse(StudentEntity resp) {
        this.id = resp.getId();
        this.firstName = resp.getFirstName();
        this.LastName = resp.getLastName();
        this.email = resp.getEmail();
        this.code = resp.getCode();
        this.course = resp.getCourseId().getCourse();
        this.paralelo = resp.getParaleloId().getParalelo();
        this.status = resp.getStatus();
        this.quimestres = resp.getQuimestres().stream().map(QuimestreResponse::new).collect(Collectors.toSet());
//        this.notas = resp.getNotas().stream().map(NotasResponse::new).collect(Collectors.toList());
    }
}

//response lo q se muestra por postman o frontend