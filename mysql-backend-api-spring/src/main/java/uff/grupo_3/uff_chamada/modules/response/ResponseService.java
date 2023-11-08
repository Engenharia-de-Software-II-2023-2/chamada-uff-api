package uff.grupo_3.uff_chamada.modules.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uff.grupo_3.uff_chamada.modules.attendance.Attendance;
import uff.grupo_3.uff_chamada.modules.attendance.AttendanceService;
import uff.grupo_3.uff_chamada.modules.attendance.AttendanceStatus;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;
    private AttendanceService attendanceService;

    @Autowired
    public ResponseService(ResponseRepository responseRepository, AttendanceService attendanceService){
        this.responseRepository = responseRepository;
        this.attendanceService = attendanceService;
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

    public List<Response> attendanceResponse(Response response) {
        return this.responseRepository.findAllByAttendanceId(response.getAttendanceId());
    }
}
