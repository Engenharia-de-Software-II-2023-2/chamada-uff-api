package uff.grupo_3.uff_chamada.justification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustificationRepository extends JpaRepository<Justification, Integer>{

}
