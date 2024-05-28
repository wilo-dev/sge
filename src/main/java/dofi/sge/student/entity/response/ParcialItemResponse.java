package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.ParcialEntity;
import dofi.sge.student.entity.model.ParcialItemEntity;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;


@Data
public class ParcialItemResponse {

    private Long id;
    private String itemParcial;

    public ParcialItemResponse(ParcialItemEntity resp) {
        this.id = resp.getId();
        this.itemParcial = resp.getNameParcial();
    }
}
