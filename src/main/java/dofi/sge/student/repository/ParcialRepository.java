package dofi.sge.student.repository;

import dofi.sge.student.entity.model.ParcialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParcialRepository extends JpaRepository<ParcialEntity, Long> {
}
