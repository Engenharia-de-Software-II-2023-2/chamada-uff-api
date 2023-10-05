package uff.grupo_3.uff_chamada.modules._class.dto.request;

public record CurrentProfessorClassesRequestDto(
    String year,
    int semester,
    String professorName
) {
    
}
