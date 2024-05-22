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

    @Column(name = "note_task", nullable = false, length = 10)
    private double noteTask;

    @Column(name = "note_group_work", nullable = false, length = 10)
    private double noteGroupWork;

    @Column(name = "note_lesson", nullable = false, length = 10)
    private double noteLesson;

    @Column(nullable = false, length = 10)
    private double exam;

    @Column(nullable = false, length = 20)
    private double promedio;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "parcial_id", nullable = false)
    private ParcialEntity parcial;

    public NotasEntity(NotasRequest data) {
        this.noteTask = data.getNoteTasks();
        this.noteGroupWork = data.getNoteGroupWorks();
        this.noteLesson = data.getNoteLessons();
        this.exam = data.getExam();
    }

    public void updateDataNota(NotasRequest data) {
        this.noteTask = data.getNoteTasks();
        this.noteGroupWork = data.getNoteGroupWorks();
        this.noteLesson = data.getNoteLessons();
        this.exam = data.getExam();
        this.setUpdatedAt(new Date());
    }

    public void calcularPromedio() {
        this.promedio = (noteTask + noteGroupWork + noteLesson + exam) / 4;
    }


//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private StudentEntity student;
//
}
