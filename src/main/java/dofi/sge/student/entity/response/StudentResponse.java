package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.StudentEntity;
import lombok.Data;


import java.util.Set;
import java.util.stream.Collectors;

@Data
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String code;

    // TODO: json infinito
    private String course;
    private String paralelo;

    private Boolean status;
    private Double promedioAnual;
    private Set<QuimestreResponse> quimestres;

    public StudentResponse(StudentEntity resp) {
        this.id = resp.getId();
        this.firstName = resp.getFirstName();
        this.lastName = resp.getLastName();
        this.email = resp.getEmail();
        this.code = resp.getCode();
        this.course = resp.getCourseId().getCourse();
        this.paralelo = resp.getParaleloId().getParalelo();
        this.status = resp.getStatus();
        this.promedioAnual = resp.getPromedioAnual();
        this.quimestres = resp.getQuimestres().stream().map(QuimestreResponse::new).collect(Collectors.toSet());
//        this.notas = resp.getNotas().stream().map(NotasResponse::new).collect(Collectors.toList());
    }
}

//todo: response lo q se muestra por postman o frontend