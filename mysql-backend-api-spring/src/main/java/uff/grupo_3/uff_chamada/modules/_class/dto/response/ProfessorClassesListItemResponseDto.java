package uff.grupo_3.uff_chamada.modules._class.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfessorClassesListItemResponseDto {
    
    private int classId;
    private String className;
    private String subjectName;
    private String schedule;
    private int semesterId;
    private String year;
    private int semester;
    private boolean isActive;
}
