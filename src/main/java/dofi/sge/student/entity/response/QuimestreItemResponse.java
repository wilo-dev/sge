package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.QuimestreEntity;
import dofi.sge.student.entity.model.QuimestreItemEntity;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class QuimestreItemResponse {

    private Long id;
    private String itemQuimestre;


    public QuimestreItemResponse(QuimestreItemEntity resp) {
        this.id = resp.getId();
        this.itemQuimestre = resp.getNameQuimestre();
    }
}
