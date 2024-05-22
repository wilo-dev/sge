package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.ParcialEntity;
import dofi.sge.student.entity.model.QuimestreEntity;
import dofi.sge.student.entity.model.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class QuimestreResponse {

    private Long id;

    private String nameQuimestre;

//    private StudentResponse student;

    private Set<ParcialResponse> parciales;

    public QuimestreResponse(QuimestreEntity resp) {
        this.id = resp.getId();
        this.nameQuimestre = resp.getNameQuimestre();
        this.parciales = resp.getParciales().stream().map(ParcialResponse::new).collect(Collectors.toSet());
    }
}
