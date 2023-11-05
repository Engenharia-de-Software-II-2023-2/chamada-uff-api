package uff.grupo_3.uff_chamada.modules.enrollment.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class StudentRecordList {
    List<StudentRecordListItem> attendanceRecordList;

    public StudentRecordList(){
        this.attendanceRecordList = new ArrayList<StudentRecordListItem>();
    }
}
