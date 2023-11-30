package uff.grupo_3.uff_chamada.modules.enrollment.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRecordListItem {
    private int attendanceId;
    private Integer studentId;
    private int classId;
    private LocalDateTime start;
    private boolean wasPresent;
}
