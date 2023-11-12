package uff.grupo_3.uff_chamada.modules.attendance.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import uff.grupo_3.uff_chamada.modules.attendance.Attendance;

@Data
@AllArgsConstructor
public class AttendancesByClassResponseDTO {

    private final List<Attendance> attendances;

    public AttendancesByClassResponseDTO(){
        this.attendances = new ArrayList<>();
    }
}
