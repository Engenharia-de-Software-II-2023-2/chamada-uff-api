package uff.grupo_3.uff_chamada.modules.attendance.dto.response;

import lombok.Data;
import uff.grupo_3.uff_chamada.modules.attendance.Attendance;

public class CreateWaitingAttendanceResponseDTO {
    private Attendance attendance;

    public CreateWaitingAttendanceResponseDTO(Attendance attendance){
        this.attendance = attendance;
    }
}
