package uff.grupo_3.uff_chamada.attendance;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Data;

enum Status{
    OPEN,
    CLOSED,
}


@Entity
@Table
@Data
@SequenceGenerator(name = "attendance_id_sequence", sequenceName = "attendance_id_sequence", allocationSize = 1)
public class Attendance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_id_sequence")
    private int Id;
    private int classId;
    private LocalDateTime start;
    private LocalTime duration;
    private Status status;
}
