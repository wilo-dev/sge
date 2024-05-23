package dofi.sge.student.repository;

import dofi.sge.student.entity.model.CoursesEntity;
import dofi.sge.student.entity.model.QuimestreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuimestreRepository extends JpaRepository<QuimestreEntity, Long> {

    List<QuimestreEntity> findByQuimestre(String quimestre);

}
