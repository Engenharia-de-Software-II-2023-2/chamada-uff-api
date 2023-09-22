package uff.grupo_3.uff_chamada.modules.enrollment.dto.response;

import java.time.LocalDateTime;

public record StudentEnrollmentResponseDto(
    int id,
    int studentId,
    String studentName,
    int classId,
    String className,
    LocalDateTime createdAt
) {
    
}
