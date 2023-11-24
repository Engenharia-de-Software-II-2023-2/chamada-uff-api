package uff.grupo_3.uff_chamada.modules.response;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {

    List<Response> findAllByAttendanceId(int attendanceId);
    
    @Query(value = "SELECT *FROM response WHERE student_id = :studentId", nativeQuery = true)
    public List<Response> findByStudentId(@Param("studentId") int studentId);

    @Query(value = "SELECT * FROM response WHERE student_id = :studentId AND attendance_id = :attendanceId",  nativeQuery = true)
    public Optional<Response> findByStudentIdAttendanceId(@Param("studentId") int studentId, @Param("attendanceId") int attendanceId);
}
