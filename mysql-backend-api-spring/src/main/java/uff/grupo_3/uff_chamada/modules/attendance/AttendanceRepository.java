package uff.grupo_3.uff_chamada.modules.attendance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
    
    @Query(
        value = "SELECT * FROM attendance WHERE class_id = :classId AND status = WAITING",
        nativeQuery = true
    )
    public Optional<Attendance> findWaitingAttendance(@Param("classId") int classId);


    @Query(value = "SELECT MAX(id) FROM attendance", nativeQuery = true)
    public int findNextId();

    @Query(value = "SELECT * FROM attendance WHERE class_id = :classId", nativeQuery = true)
    public List<Attendance> findByClassId(@Param("classId") int classId);

    @Query(value = "SELECT a from Attendance a  WHERE a.classId = :classId AND a.status = uff.grupo_3.uff_chamada.modules.attendance.AttendanceStatus.ACTIVE")
    public List<Attendance> findOpenAttendances(@Param("classId") int classId);

}
