package uff.grupo_3.uff_chamada.modules.enrollment.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentEnrollmentResponseListDto {
    
    private final List<StudentEnrollmentResponseListItemDto> enrollments;

    public StudentEnrollmentResponseListDto(){
        enrollments = new ArrayList<StudentEnrollmentResponseListItemDto>();
    }
}
