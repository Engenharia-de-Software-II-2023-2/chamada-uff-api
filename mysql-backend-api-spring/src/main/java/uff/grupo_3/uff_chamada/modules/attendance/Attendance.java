package uff.grupo_3.uff_chamada.modules.attendance;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table
@Data
@AllArgsConstructor
@SequenceGenerator(name = "attendance_id_sequence", sequenceName = "attendance_id_sequence", allocationSize = 1, initialValue = 4)
public class Attendance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_id_sequence")
    private int id;
    private int classId;
    private LocalDateTime start;
    private LocalTime duration;
    @Column(name = "latitude", nullable = true)
    private double latitude;
    @Column(name = "logitude", nullable = true)
    private double longitude;
    @Column(name = "radius", nullable = true)
    private double radius;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    public Attendance(int id, int classId, LocalDateTime start, LocalTime duration, AttendanceStatus status){
        this.id = id;
        this.classId = classId;
        this.start = start;
        this.duration = duration;
        this.status = status;
    }
}
