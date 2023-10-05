package uff.grupo_3.uff_chamada.modules.attendance.dto.request;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class CreateWaitingAttendanceDTO{
    private int classId;
    private LocalDateTime start;
    private LocalTime duration;
    private double latitude;
    private double longitude;
    private double radius;
}
