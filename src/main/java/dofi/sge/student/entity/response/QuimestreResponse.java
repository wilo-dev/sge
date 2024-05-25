package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.QuimestreEntity;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class QuimestreResponse {

    private Long id;

    private String quimestre;

    private Double promedioQuimestral;

    private Set<ParcialResponse> parciales;

    //    private StudentResponse student;


    public QuimestreResponse(QuimestreEntity resp) {
        this.id = resp.getId();
        this.quimestre = resp.getQuimestre();
        this.promedioQuimestral = resp.getPromedioQuimestral();
        this.parciales = resp.getParciales().stream().map(ParcialResponse::new).collect(Collectors.toSet());
    }
}
