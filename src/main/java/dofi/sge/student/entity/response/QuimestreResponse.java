package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.QuimestreEntity;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class QuimestreResponse {

    private Long id;

    private String quimestre;

//    private StudentResponse student;

    private Set<ParcialResponse> parciales;

    public QuimestreResponse(QuimestreEntity resp) {
        this.id = resp.getId();
        this.quimestre = resp.getQuimestre();
        this.parciales = resp.getParciales().stream().map(ParcialResponse::new).collect(Collectors.toSet());
    }
}
