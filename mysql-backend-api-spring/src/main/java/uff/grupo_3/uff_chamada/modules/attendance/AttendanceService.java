package uff.grupo_3.uff_chamada.modules.attendance;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uff.grupo_3.uff_chamada.modules.attendance.dto.response.AttendancesByClassResponseDTO;

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

    public int createAttendance(int classId) throws Exception{
        Attendance newAttendance = new Attendance();
        newAttendance.setClassId(classId);
        newAttendance.setStart(null);
        newAttendance.setDuration(null);
        newAttendance.setLatitude(null);
        newAttendance.setLongitude(null);
        newAttendance.setRadius(null);
        newAttendance.setStatus(AttendanceStatus.WAITING);

        this.attendanceRepository.save(newAttendance);
        // return attendanceRepository.findById(newAttendance.getId()).orElseThrow(() -> new Exception("unable to create attendance"));
        return newAttendance.getId();
    }

    public void updateAttendance(Attendance attendance){
        this.attendanceRepository.save(attendance);
    }

    public void controlAttendance(Attendance attendance) {
        Attendance existingAttendance = getAttendance(attendance.getId());
        if(existingAttendance != null){
            if(existingAttendance.getStatus() == AttendanceStatus.WAITING || existingAttendance.getStatus() == AttendanceStatus.OVER){
                existingAttendance.setStart(LocalDateTime.now());
                existingAttendance.setDuration(null);
                existingAttendance.setStatus(AttendanceStatus.ACTIVE);
                existingAttendance.setLatitude(attendance.getLatitude());
                existingAttendance.setLongitude(attendance.getLongitude());
                existingAttendance.setRadius(attendance.getRadius());
                this.attendanceRepository.save(existingAttendance);
                return;
            }
            if(existingAttendance.getStatus() == AttendanceStatus.ACTIVE){
                Duration duration = Duration.between(existingAttendance.getStart(), LocalDateTime.now());
                existingAttendance.setDuration(duration.toMinutes());
                existingAttendance.setStatus(AttendanceStatus.OVER);
                this.attendanceRepository.save(existingAttendance);
                return;
            }
        }
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

    public AttendancesByClassResponseDTO findAttendancesByClass(int classId){
        return new AttendancesByClassResponseDTO(attendanceRepository.findByClassId(classId));
    }

    public Attendance checkAttendanceStatus(int attendanceId) throws Exception{
        return attendanceRepository.findById(attendanceId).orElseThrow(() ->  new IllegalStateException("chamada de id " + attendanceId + "não existe"));
    }
}
