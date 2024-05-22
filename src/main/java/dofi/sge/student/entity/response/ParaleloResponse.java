package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.CoursesEntity;
import dofi.sge.student.entity.model.ParaleloEntity;
import lombok.Data;

@Data
public class ParaleloResponse {
    private Long id;
    private String paralelo;

    public ParaleloResponse(ParaleloEntity resp) {
        this.id = resp.getId();
        this.paralelo = resp.getParalelo();
    }
}
