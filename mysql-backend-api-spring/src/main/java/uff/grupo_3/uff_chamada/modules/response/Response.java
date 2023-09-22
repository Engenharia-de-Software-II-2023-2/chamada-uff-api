package uff.grupo_3.uff_chamada.modules.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "response_generator_id", sequenceName = "response_generator_id", allocationSize = 1, initialValue = 4)
public class Response {
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "response_generator_id")
    @Id
    private int id;
    private int studentId;
    private int attendanceId;
    private LocalDateTime start;
    private LocalDateTime end;

}
