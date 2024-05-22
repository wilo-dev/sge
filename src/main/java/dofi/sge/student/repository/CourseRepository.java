package dofi.sge.student.repository;

import dofi.sge.student.entity.model.CoursesEntity;
import dofi.sge.student.entity.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CoursesEntity, Long> {

    List<CoursesEntity> findByCourse(String course);

}
