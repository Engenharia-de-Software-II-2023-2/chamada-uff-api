package uff.grupo_3.uff_chamada.modules._class.dto.request;

import lombok.Data;

@Data
public class ProfessorClassesRequestDto {
    
    private int professorId;
    private String year;
    private int semester;
}
