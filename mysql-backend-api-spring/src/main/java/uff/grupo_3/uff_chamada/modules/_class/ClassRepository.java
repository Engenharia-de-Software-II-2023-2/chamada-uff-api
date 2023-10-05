package uff.grupo_3.uff_chamada.modules._class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassRepository extends JpaRepository<Class, Integer>{
}
