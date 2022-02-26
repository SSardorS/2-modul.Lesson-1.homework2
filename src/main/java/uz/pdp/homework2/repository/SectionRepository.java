package uz.pdp.homework2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.homework2.entity.Section;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<Section> existsByName(String name);


    boolean existsByParentId(Long parent_id);

    @Query(value = "select * from section s where s.parent_id=: parentId;", nativeQuery = true)
    List<Section> findParentIdNative(Long parentId);

}
