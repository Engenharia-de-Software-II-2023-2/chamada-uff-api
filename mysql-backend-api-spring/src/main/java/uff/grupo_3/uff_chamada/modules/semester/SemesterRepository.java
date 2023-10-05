package uff.grupo_3.uff_chamada.modules.semester;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    
    Optional<List<Semester>> findByYear(String year);
}
