package dofi.sge.student.repository;

import dofi.sge.student.entity.model.CoursesEntity;
import dofi.sge.student.entity.model.NotasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<NotasEntity, Long> {

    List<NotasEntity> findByExam(double exam);

}
