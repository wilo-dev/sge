package dofi.sge.student.entity.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuimestreItemRequest {

    private String itemQuimestre;
    private Boolean status;

//    private Set<QuimestreEntity> quimestre = new HashSet<>();;


}
