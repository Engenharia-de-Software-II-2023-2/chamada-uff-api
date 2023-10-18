package uff.grupo_3.uff_chamada.modules.attendance;

import java.time.LocalDateTime;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    private AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository){
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance getAttendance(int id){
        return this.attendanceRepository.findById(id).orElseThrow(() -> new IllegalStateException("attedance de id" + id + " não existe"));
    }

    public void createAttendance(int classId){
        Attendance newAttendance = new Attendance();
        newAttendance.setClassId(classId);
        newAttendance.setStart(null);
        newAttendance.setDuration(null);
        newAttendance.setStatus(Status.WAITING);

        this.attendanceRepository.save(newAttendance);
    }

    public void updateAttendance(Attendance attendance){
        this.attendanceRepository.save(attendance);
    }

    public void controlAttendance(Attendance attendance) {
        Attendance existingAttendance = getAttendance(attendance.getId());
        if(existingAttendance != null){
            if(existingAttendance.getStatus() == Status.WAITING || existingAttendance.getStatus() == Status.OVER){
                existingAttendance.setStart(LocalDateTime.now());
                existingAttendance.setDuration(null);
                existingAttendance.setStatus(Status.ACTIVE);
                existingAttendance.setLatitude(attendance.getLatitude());
                existingAttendance.setLongitude(attendance.getLongitude());
                this.attendanceRepository.save(existingAttendance);
                return;
            }
            if(existingAttendance.getStatus() == Status.ACTIVE){
                Duration duration = Duration.between(existingAttendance.getStart(), LocalDateTime.now());
                existingAttendance.setDuration(duration.toMinutes());
                existingAttendance.setStatus(Status.OVER);
                this.attendanceRepository.save(existingAttendance);
                return;
            }
        }
    }

    public void deleteAttendance(int id){
        this.attendanceRepository.deleteById(id);
    }

}
