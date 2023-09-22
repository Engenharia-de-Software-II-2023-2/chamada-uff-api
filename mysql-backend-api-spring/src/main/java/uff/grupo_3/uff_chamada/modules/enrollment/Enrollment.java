package uff.grupo_3.uff_chamada.modules.enrollment;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Table
@Entity
@Data
@SequenceGenerator(name = "enrollment_id_seq", sequenceName = "enrollment_id_seq", allocationSize = 1)
public class Enrollment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrollment_id_seq")
    private int id;
    private int studentId;
    private int classId;
    private LocalDateTime createdAt;
}
