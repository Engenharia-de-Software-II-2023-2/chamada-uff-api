package uff.grupo_3.uff_chamada.modules.enrollment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentEnrollmentListItemDto {
    
    private int id;
    private int classId;
    private String className;
    private String subjectName;
    private String schedule;
    private int professorId;
    private String professorName;
    private int semesterId;
    private String year;
    private int semester;
    private boolean isActive;
}
