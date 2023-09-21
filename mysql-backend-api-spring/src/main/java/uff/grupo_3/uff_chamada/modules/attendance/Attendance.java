package uff.grupo_3.uff_chamada.modules.attendance;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Table
@Data
@SequenceGenerator(name = "attendance_id_sequence", sequenceName = "attendance_id_sequence", allocationSize = 1, initialValue = 4)
public class Attendance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_id_sequence")
    private int id;
    private int classId;
    private LocalDateTime start;
    private LocalTime duration;
    @Enumerated(EnumType.STRING)
    private Status status;
}
