package uff.grupo_3.uff_chamada.modules.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AttendanceRepositoyTests {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @AfterEach
    void tearDown(){
        attendanceRepository.deleteAll();
    }
    
    @Test
    public void testFindByClassId() {
        Attendance attendance1 = new Attendance();
        attendance1.setId(4);
        attendance1.setClassId(1);
        attendance1.setStatus(AttendanceStatus.WAITING);
        attendanceRepository.save(attendance1);

        Attendance attendance2 = new Attendance();
        attendance2.setId(5);
        attendance2.setClassId(1);
        attendance2.setStatus(AttendanceStatus.WAITING);
        attendanceRepository.save(attendance2);

        Attendance attendance3 = new Attendance();
        attendance3.setId(6);
        attendance3.setClassId(2);
        attendance3.setStatus(AttendanceStatus.WAITING);
        attendanceRepository.save(attendance3);

        List<Attendance> attendanceList = attendanceRepository.findByClassId(1);

        // Assert that the list returns only the attendances with classId = 1 (2 attendances)
        assertEquals(2, attendanceList.size());

        assertEquals(attendance1.getId(), attendanceList.get(0).getId());
        assertEquals(attendance1.getClassId(), attendanceList.get(0).getClassId());
        assertEquals(attendance1.getStatus(), attendanceList.get(0).getStatus());

        assertEquals(attendance2.getId(), attendanceList.get(1).getId());
        assertEquals(attendance2.getClassId(), attendanceList.get(1).getClassId());
        assertEquals(attendance2.getStatus(), attendanceList.get(1).getStatus());
    }

    @Test
    public void testFindOpenAttendances() {
        Attendance attendance1 = new Attendance();
        attendance1.setId(4);
        attendance1.setClassId(1);
        attendance1.setStatus(AttendanceStatus.ACTIVE);
        attendanceRepository.save(attendance1);

        Attendance attendance2 = new Attendance();
        attendance2.setId(5);
        attendance2.setClassId(1);
        attendance2.setStatus(AttendanceStatus.OVER);
        attendanceRepository.save(attendance2);

        Attendance attendance3 = new Attendance();
        attendance3.setId(6);
        attendance3.setClassId(1);
        attendance3.setStatus(AttendanceStatus.ACTIVE);
        attendanceRepository.save(attendance3);

        List<Attendance> attendanceList = attendanceRepository.findOpenAttendances(1);

        // Assert that the list returns only the attendances that are active (2 attendances)
        assertEquals(2, attendanceList.size());

        assertEquals(attendance1.getId(), attendanceList.get(0).getId());
        assertEquals(attendance1.getClassId(), attendanceList.get(0).getClassId());
        assertEquals(attendance1.getStatus(), attendanceList.get(0).getStatus());

        assertEquals(attendance3.getId(), attendanceList.get(1).getId());
        assertEquals(attendance3.getClassId(), attendanceList.get(1).getClassId());
        assertEquals(attendance3.getStatus(), attendanceList.get(1).getStatus());
    }
}
