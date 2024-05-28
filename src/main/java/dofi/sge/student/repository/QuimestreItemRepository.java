package dofi.sge.student.repository;

import dofi.sge.student.entity.model.QuimestreEntity;
import dofi.sge.student.entity.model.QuimestreItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuimestreItemRepository extends JpaRepository<QuimestreItemEntity, Long> {

    List<QuimestreItemEntity> findByNameQuimestre(String nombre);

}

