package uff.grupo_3.uff_chamada.modules._class.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProfessorClassesListResponseDto {
    
    private List<ProfessorClassesListItemResponseDto> professorClasses;

    public ProfessorClassesListResponseDto(){
        this.professorClasses = new ArrayList<ProfessorClassesListItemResponseDto>();
    }
}
