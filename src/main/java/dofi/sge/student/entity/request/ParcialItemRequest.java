package dofi.sge.student.entity.request;

import dofi.sge.student.entity.model.NotasEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcialItemRequest {
    private String itemParcial;
    private Boolean status;
}
