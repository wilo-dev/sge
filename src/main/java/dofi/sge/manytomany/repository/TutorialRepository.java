package dofi.sge.manytomany.repository;

import dofi.sge.manytomany.entity.models.TutorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<TutorialEntity, Long> {
    List<TutorialEntity> findTutorialsByTagsId(Long tagId);

    List<TutorialEntity> findByTitleContaining(String title);
}
