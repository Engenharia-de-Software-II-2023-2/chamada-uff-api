package uff.grupo_3.uff_chamada.modules.attendance;

import java.util.List;

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

    public void createAttedance(Attendance attendance){
        this.attendanceRepository.save(attendance);
    }

    public void updateAttendance(Attendance attendance){
        this.attendanceRepository.save(attendance);
    }

    public void deleteAttendance(int id){
        this.attendanceRepository.deleteById(id);
    }

    public Attendance createWaitingAttendance(Attendance attendance){
        attendance.setStatus(AttendanceStatus.WAITING);
        attendanceRepository.save(attendance);
        return attendance;
    }

    public Attendance createActiveAttendance(Attendance attendance){
        attendance.setStatus(AttendanceStatus.ACTIVE);
        attendanceRepository.save(attendance);
        return attendance;
    }

    public List<Attendance> getActiveAttendances(int id){
        return attendanceRepository.findOpenAttendances(id);
    }

    public List<Attendance> getAttendancesByClassId(int id){
        return attendanceRepository.findByClassId(id);
    }

}
