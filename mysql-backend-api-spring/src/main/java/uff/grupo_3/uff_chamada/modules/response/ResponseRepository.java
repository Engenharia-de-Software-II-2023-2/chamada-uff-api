package uff.grupo_3.uff_chamada.modules.response;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {
    
    @Query(value = "SELECT *FROM response WHERE student_id = :studentId", nativeQuery = true)
    public List<Response> findByStudentId(@Param("studentId") int studentId);
}
