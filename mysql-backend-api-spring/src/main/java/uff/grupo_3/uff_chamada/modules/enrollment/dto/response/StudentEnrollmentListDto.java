package uff.grupo_3.uff_chamada.modules.enrollment.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class StudentEnrollmentListDto {
    
    private List<StudentEnrollmentListItemDto> enrollments;

    public StudentEnrollmentListDto(){
        this.enrollments = new ArrayList<StudentEnrollmentListItemDto>();
    }
    
}

