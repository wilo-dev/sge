package dofi.sge.student.repository;

import dofi.sge.student.entity.model.ParcialEntity;
import dofi.sge.student.entity.model.ParcialItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcialItemRepository extends JpaRepository<ParcialItemEntity, Long> {
    List<ParcialItemEntity> findByNameParcial(String parcial);

}

