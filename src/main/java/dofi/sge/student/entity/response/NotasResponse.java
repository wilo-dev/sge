package dofi.sge.student.entity.response;

import dofi.sge.student.entity.model.NotasEntity;
import lombok.Data;

@Data
public class NotasResponse {
    private Long id;
    private double noteTask;
    private double noteGroupWork;
    private double noteLesson;
    private double exam;
    private double promedio;
//    private String parcial;


    public NotasResponse(NotasEntity resp) {
        this.id = resp.getId();
        this.noteTask = resp.getNoteTask();
        this.noteGroupWork = resp.getNoteGroupWork();
        this.noteLesson = resp.getNoteLesson();
        this.exam = resp.getExam();
        this.promedio = resp.getPromedio();
//        this.parcial = resp.getParcialId().getParcial();
    }
}
