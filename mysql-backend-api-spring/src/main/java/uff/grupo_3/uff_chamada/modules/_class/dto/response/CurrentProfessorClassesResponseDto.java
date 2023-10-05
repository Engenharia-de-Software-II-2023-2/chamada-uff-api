package uff.grupo_3.uff_chamada.modules._class.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import uff.grupo_3.uff_chamada.modules._class.Class;

@Data
public class CurrentProfessorClassesResponseDto {
    private final List<Class> classes;

    public CurrentProfessorClassesResponseDto() {
        this.classes = new ArrayList<>();
    }
}