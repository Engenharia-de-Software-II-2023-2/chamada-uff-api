package uff.grupo_3.uff_chamada.modules.enrollment.dto.request;

import lombok.Data;

@Data
public class StudentEnrollmentsRequestDto {
    private int studentId;
    private String year;
    private int semester;
}
