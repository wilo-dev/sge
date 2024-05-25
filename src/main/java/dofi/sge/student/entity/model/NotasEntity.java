package dofi.sge.student.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dofi.sge.student.entity.request.NotasRequest;
import dofi.sge.util.RangoNota;
import dofi.sge.util.entity.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @RangoNota
    @NotNull(message = "noteTask no puede ser nulo")
    @Column(name = "note_task")
    private Double noteTask;

    @RangoNota
    @NotNull(message = "noteGroupWork no puede ser nulo")
    @Column(name = "note_group_work")
    private Double noteGroupWork;

    @RangoNota
    @NotNull(message = "noteLesson no puede ser nulo")
    @Column(name = "note_lesson")
    private Double noteLesson;

    @RangoNota
    @NotNull(message = "exam no puede ser nulo")
    @Column(name = "examen")
    private Double exam;

    @Column(name = "promedio_nota")
    private Double promedio;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "parcial_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ParcialEntity parcialId;

    public NotasEntity(NotasRequest data, ParcialEntity parcialId) {
        this.noteTask = data.getNoteTasks();
        this.noteGroupWork = data.getNoteGroupWorks();
        this.noteLesson = data.getNoteLessons();
        this.exam = data.getExam();
        this.parcialId = parcialId;
        this.promedio = calcularPromedio();
    }

    public void updateDataNota(NotasRequest data) {
        this.noteTask = data.getNoteTasks();
        this.noteGroupWork = data.getNoteGroupWorks();
        this.noteLesson = data.getNoteLessons();
        this.exam = data.getExam();
        this.promedio = calcularPromedio();
        this.setUpdatedAt(new Date());
    }

    @Transient
    public Double calcularPromedio() {
        return this.promedio = (noteTask + noteGroupWork + noteLesson + exam) / 4;
    }
}
