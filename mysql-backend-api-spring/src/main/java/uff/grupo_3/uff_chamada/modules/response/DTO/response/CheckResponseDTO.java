package uff.grupo_3.uff_chamada.modules.response.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckResponseDTO{
    private Integer responseId;
    private int studentId;
    private int attendanceId;
    private boolean hasResponded;
} 
