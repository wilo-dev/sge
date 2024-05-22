package dofi.sge.student.repository;

import dofi.sge.student.entity.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findByStatusTrue();

    List<StudentEntity> findByStatusFalse();

    List<StudentEntity> findByStatus(boolean status);

//    List<StudentEntity> findByCourse(Long id);

//    Optional<StudentEntity> findByIdAndStatusTrue(Long id, Boolean status);

    Optional<StudentEntity> findByIdAndStatus(Long id, Boolean status);

//    List<StudentEntity> findByFirstName(String firstName);
//
//    List<StudentEntity> findByLastName(String lastName);

    List<StudentEntity> findByEmail(String email);


}
