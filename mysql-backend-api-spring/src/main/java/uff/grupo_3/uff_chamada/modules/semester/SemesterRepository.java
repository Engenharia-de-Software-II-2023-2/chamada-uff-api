package uff.grupo_3.uff_chamada.modules.semester;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    
    @Query(value = "SELECT * FROM semester WHERE year = :year AND semester = :semester", nativeQuery = true)
    public Optional<Semester> findSemesterByYearAndSemester(@Param("year") String year, @Param("semester") int semester);
}
