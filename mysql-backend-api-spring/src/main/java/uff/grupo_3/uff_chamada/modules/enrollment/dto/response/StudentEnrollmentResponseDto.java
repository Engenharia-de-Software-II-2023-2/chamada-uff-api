package uff.grupo_3.uff_chamada.modules.enrollment.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import uff.grupo_3.uff_chamada.modules.enrollment.Enrollment;

@Data
@AllArgsConstructor
public class StudentEnrollmentResponseDto {
    
    private final List<Enrollment> enrollments;
}
