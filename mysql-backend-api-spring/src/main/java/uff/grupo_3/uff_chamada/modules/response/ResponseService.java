package uff.grupo_3.uff_chamada.modules.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uff.grupo_3.uff_chamada.modules.attendance.Attendance;
import uff.grupo_3.uff_chamada.modules.attendance.AttendanceService;
import uff.grupo_3.uff_chamada.modules.attendance.AttendanceStatus;
import uff.grupo_3.uff_chamada.modules.response.DTO.response.CheckResponseDTO;
import uff.grupo_3.uff_chamada.modules.enrollment.Enrollment;
import uff.grupo_3.uff_chamada.modules.enrollment.EnrollmentService;
import uff.grupo_3.uff_chamada.modules.user.User;
import uff.grupo_3.uff_chamada.modules.user.UserService;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;
    private AttendanceService attendanceService;
    private UserService userService;
    private EnrollmentService enrollmentService;

    @Autowired
    public ResponseService(ResponseRepository responseRepository, AttendanceService attendanceService, UserService userService, EnrollmentService enrollmentService){
        this.responseRepository = responseRepository;
        this.attendanceService = attendanceService;
        this.userService = userService;
        this.enrollmentService = enrollmentService;
    }

    public Response getResponse(int id){
        return this.responseRepository.findById(id).orElseThrow(
            () -> new IllegalStateException("response de id " + id + " não existe"));
    }

    public List<Response> listResponse() {
        return this.responseRepository.findAll();
    }

    public boolean createResponse(Response response){
        Attendance attendance = attendanceService.getAttendance(response.getAttendanceId());
        if(attendance.getStatus() == AttendanceStatus.ACTIVE){
            response.setStart(LocalDateTime.now());
            response.setValid(true);
            this.responseRepository.save(response);
            return true;
        }
        else{
            return false;
        }
    }

    public void updateResponse(Response response){
        this.responseRepository.save(response);
    }

    public void validateResponse(Response response){
        Response existingResponse = getResponse(response.getId());
        if(existingResponse != null){
            if(existingResponse.isValid() == true){
                existingResponse.setValid(false);
            }
            else{
                existingResponse.setValid(true);
            }
            this.responseRepository.save(existingResponse);
        }
    }

    public void deleteResponse(int id){
        this.responseRepository.deleteById(id);
    }

    public Map<String, List<Map<String, Object>>> attendanceResponse(Response response) throws Exception{
        try {
            Map<String, List<Map<String, Object>>> attendanceMap = new HashMap<>();
            ArrayList<Map<String, Object>> present = new ArrayList<Map<String, Object>>();
            ArrayList<Map<String, Object>> absent = new ArrayList<Map<String, Object>>();
            List<Response> attendanceResponse;
            List<Enrollment> classEnrollments;
    
            attendanceResponse = this.responseRepository.findAllByAttendanceId(response.getAttendanceId());
            for (Response resp : attendanceResponse) {
                User student = userService.getUserById(resp.getStudentId());
                Map<String, Object> studentResponse = new HashMap<String, Object>();
                studentResponse.put("name", student.getName());
                studentResponse.put("id", resp.getStudentId());
                present.add(studentResponse);
            }
    
            classEnrollments = enrollmentService.classEnrollments(attendanceService.getAttendance(response.getAttendanceId()).getClassId());
            for (Enrollment enroll : classEnrollments) {
                Map<String, Object> studentResponse = new HashMap<String, Object>();
                studentResponse.put("name", userService.getUserById(enroll.getStudentId()).getName());
                studentResponse.put("id", enroll.getStudentId());
                if (!present.contains(studentResponse)) {
                    absent.add(studentResponse);
                }
            }
    
            attendanceMap.put("absent", absent);
            attendanceMap.put("present", present);
            
            return attendanceMap;
        } catch (Exception e){
            throw e;
        }
    }

    public CheckResponseDTO findByStudentIdAttendanceId(int studentId, int attendanceId){
        Optional<Response> responseOpt = responseRepository.findByStudentIdAttendanceId(studentId, attendanceId);

        Integer responseId = responseOpt.isPresent() ? responseOpt.get().getId() : null;
        boolean hasResponded = responseId != null;

        return new CheckResponseDTO(responseId, studentId, attendanceId, hasResponded);
    }
}
