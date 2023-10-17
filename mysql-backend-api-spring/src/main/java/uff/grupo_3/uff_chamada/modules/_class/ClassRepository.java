package uff.grupo_3.uff_chamada.modules._class;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassRepository extends JpaRepository<Class, Integer>{
    
    @Query(value = "SELECT * FROM class WHERE professor_id = :professorId", nativeQuery = true)
    public List<Class> findClassByProfessorId(@Param("professorId") int professorId);
    
}
