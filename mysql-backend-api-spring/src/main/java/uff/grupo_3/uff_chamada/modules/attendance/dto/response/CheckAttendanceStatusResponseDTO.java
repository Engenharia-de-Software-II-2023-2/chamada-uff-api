package uff.grupo_3.uff_chamada.modules.attendance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAttendanceStatusResponseDTO {
    private boolean isActive;
    private Double latitude;
    private Double longitude;
    private Double radius; 
}
