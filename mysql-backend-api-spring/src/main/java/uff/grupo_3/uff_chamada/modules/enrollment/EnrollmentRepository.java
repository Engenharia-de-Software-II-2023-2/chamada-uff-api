package uff.grupo_3.uff_chamada.modules.enrollment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentResponseDto;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
    
    Optional<List<Enrollment>> findByStudentId(int studentId);

    @Query(
        value = """
            with 
            r1 AS (SELECT `id`, `name` FROM user),
        
            r2 AS (SELECT `id`, name FROM class),
            
            r3 as (SELECT e.id, e.student_id, e.class_id, e.created_at, r1.name as user_name 
                FROM enrollment as e JOIN r1 on r1.id = e.student_id)
                
             SELECT new uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentResponseDto(r3.id, r3.student_id, r3.user_name, r3.class_id, r2.name, r3.created_at)
                 FROM r3 JOIN r2 on r3.class_id = r2.id WHERE student_id = :studentId
                """,
        nativeQuery = true
    )
    Optional<List<StudentEnrollmentResponseDto>> findStudentEnrollments(@Param("studentId") int studentId);
}
