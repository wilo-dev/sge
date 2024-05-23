package dofi.sge.student.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dofi.sge.student.entity.request.NotasRequest;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notas")
public class NotasEntity extends AuditableEntity {

    @Column(name = "note_task", nullable = false)
    private Double noteTask;

    @Column(name = "note_group_work", nullable = false)
    private Double noteGroupWork;

    @Column(name = "note_lesson", nullable = false)
    private Double noteLesson;

    @Column(nullable = false)
    private Double exam;

    @Column(nullable = false)
    private Double promedio;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "parcial_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ParcialEntity parcialId;

    public NotasEntity(NotasRequest data) {
        this.noteTask = data.getNoteTasks();
        this.noteGroupWork = data.getNoteGroupWorks();
        this.noteLesson = data.getNoteLessons();
        this.exam = data.getExam();
        this.parcialId = data.getParcialId();
        setPromedio(calcularPromedio());
    }

    public void updateDataNota(NotasRequest data) {
        this.noteTask = data.getNoteTasks();
        this.noteGroupWork = data.getNoteGroupWorks();
        this.noteLesson = data.getNoteLessons();
        this.exam = data.getExam();
        this.parcialId = data.getParcialId();
        this.setUpdatedAt(new Date());
    }

    public Double calcularPromedio() {

        return this.promedio = (noteTask + noteGroupWork + noteLesson + exam) / 4;
    }


//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private StudentEntity student;
//
}
