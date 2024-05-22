package dofi.sge.manytomany.repository;

import dofi.sge.manytomany.entity.models.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findTagsByTutorialsId(Long tutorialId);
}
