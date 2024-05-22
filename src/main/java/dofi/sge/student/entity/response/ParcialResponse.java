package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.NotasEntity;
import dofi.sge.student.entity.model.ParcialEntity;
import dofi.sge.student.entity.model.QuimestreEntity;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;


@Data
public class ParcialResponse {

    private Long id;

    private String nameParcial;

    private QuimestreEntity quimestre;

    private Set<NotasResponse> notas;

    public ParcialResponse(ParcialEntity resp) {
        this.id = resp.getId();
        this.nameParcial = resp.getNameParcial();
        this.quimestre = resp.getQuimestre();
        this.notas = resp.getNotas().stream().map(NotasResponse::new).collect(Collectors.toSet());
    }
}
