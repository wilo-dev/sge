package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.QuimestreEntity;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class QuimestreResponse {

    private Long id;

    // json infinito
    private String nameQuimestre;

    private Double promedioQuimestral;

    private Set<ParcialResponse> parciales;

    public QuimestreResponse(QuimestreEntity resp) {
        this.id = resp.getId();
        this.nameQuimestre = resp.getItemQuimestreId().getNameQuimestre();
        this.parciales = resp.getParciales().stream().map(ParcialResponse::new).collect(Collectors.toSet());
        this.promedioQuimestral = resp.getPromedioQuimestral();
    }
}
