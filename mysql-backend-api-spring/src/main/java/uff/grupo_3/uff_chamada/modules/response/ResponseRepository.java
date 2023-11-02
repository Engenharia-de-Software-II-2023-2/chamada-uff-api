package uff.grupo_3.uff_chamada.modules.response;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {

    List<Response> findAllByAttendanceId(int attendanceId);
    
}
