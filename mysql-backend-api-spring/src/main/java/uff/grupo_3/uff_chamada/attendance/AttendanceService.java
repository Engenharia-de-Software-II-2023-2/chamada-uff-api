package uff.grupo_3.uff_chamada.attendance;

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

}
