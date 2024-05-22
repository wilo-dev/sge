package dofi.sge.student.repository;

import dofi.sge.student.entity.model.CoursesEntity;
import dofi.sge.student.entity.model.ParaleloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParaleloRepository extends JpaRepository<ParaleloEntity, Long> {

    List<ParaleloEntity> findByParalelo(String paralelo);

}
