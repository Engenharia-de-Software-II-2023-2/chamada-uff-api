package uff.grupo_3.uff_chamada.modules.attendance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import uff.grupo_3.uff_chamada.modules.attendance.dto.request.CheckAttendanceStatusRequestDTO;
import uff.grupo_3.uff_chamada.modules.attendance.dto.request.CreateAttendanceRequestDTO;
import uff.grupo_3.uff_chamada.modules.attendance.dto.request.AttendancesByClassRequestDTO;
import uff.grupo_3.uff_chamada.modules.attendance.dto.response.CheckAttendanceStatusResponseDTO;
import uff.grupo_3.uff_chamada.modules.attendance.dto.response.createAttendanceResponseDTO;
import uff.grupo_3.uff_chamada.modules.attendance.dto.response.AttendancesByClassResponseDTO;

@Tag(name = "Attendance", description = "Attendance Requests")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // ROLE_ADMIN == PROFESSOR
    // ROLE_USER == STUDENT

    @GetMapping(path = "/getAttendance/{id}", produces = "application/json")
    @ResponseBody
    public Attendance getAttendance(@PathVariable("id") int id) {
        return this.attendanceService.getAttendance(id);
    }

    @PostMapping(path = "/createAttendance")
    public ResponseEntity createAttendance(@RequestBody CreateAttendanceRequestDTO request, @AuthenticationPrincipal UserDetails userDetails) {
        
        try {
            String userRole = userDetails.getAuthorities().iterator().next().getAuthority();
            if ("ROLE_ADMIN".equals(userRole)) {
                int newAttendanceId = this.attendanceService.createAttendance(request.getClassId());

                return ResponseEntity.ok(new createAttendanceResponseDTO(newAttendanceId));
            }else {
                return ResponseEntity.badRequest().body("somente professor pode criar chamada");
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

    @PutMapping(path = "/updateAttendance", produces = "aplication/json")
    public ResponseEntity<Void> updateAttendance(@RequestBody Attendance attendance,
            @AuthenticationPrincipal UserDetails userDetails) {
        String userRole = userDetails.getAuthorities().iterator().next().getAuthority();
        if ("ROLE_ADMIN".equals(userRole)) {
            this.attendanceService.updateAttendance(attendance);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(path = "/controlAttendance", produces = "aplication/json")
    public ResponseEntity<Void> controlAttendance(@RequestBody Attendance attendance,
            @AuthenticationPrincipal UserDetails userDetails) {
        String userRole = userDetails.getAuthorities().iterator().next().getAuthority();
        if ("ROLE_ADMIN".equals(userRole)) {
            this.attendanceService.controlAttendance(attendance);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "/deleteAttendance/{id}", produces = "aplication/json")
    public ResponseEntity<Void> deleteAttendance(@PathVariable("id") int id,
            @AuthenticationPrincipal UserDetails userDetails) {
        String userRole = userDetails.getAuthorities().iterator().next().getAuthority();
        if ("ROLE_ADMIN".equals(userRole)) {
            this.attendanceService.deleteAttendance(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(path = "/createWaitingAttendance")
    public ResponseEntity<Attendance> createWaitingAttendance(@RequestBody Attendance attendance) {
        return ResponseEntity.ok((attendanceService.createWaitingAttendance(attendance)));
    }

    @PostMapping(path = "/createActiveAttendance")
    public ResponseEntity<Attendance> createActiveAttendance(@RequestBody Attendance attendance) {
        return ResponseEntity.ok(attendanceService.createActiveAttendance(attendance));
    }

    @GetMapping(path = "/getActiveAttendances/{id}")
    @ResponseBody
    public List<Attendance> getActiveAttendances(@PathVariable("id") int id) {
        return this.attendanceService.getActiveAttendances(id);
    }

    @GetMapping(path = "/findAttendancesByClassId/{id}")
    @ResponseBody
    public List<Attendance> findAttendancesByClassId(@PathVariable("id") int id) {
        return this.attendanceService.getAttendancesByClassId(id);
    }

    @PostMapping(path = "/findAttendancesByClass")
    public ResponseEntity<Object> findAttendancesByClass(@RequestBody AttendancesByClassRequestDTO request){
        try {
            return ResponseEntity.ok(attendanceService.findAttendancesByClass(request.getClassId()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path = "/checkAttendanceStatus")
    public ResponseEntity<CheckAttendanceStatusResponseDTO> checkAttendanceStatus(
            @RequestBody CheckAttendanceStatusRequestDTO request) {
        try {
            Attendance attendance = attendanceService.checkAttendanceStatus(request.getAttendanceId());
            
            Double responseLatitude = attendance.getStatus() == AttendanceStatus.ACTIVE ? attendance.getLatitude() : null; 
            Double responseLongitute = attendance.getStatus() == AttendanceStatus.ACTIVE ? attendance.getLongitude() : null; 
            Double responseRadius = attendance.getStatus() == AttendanceStatus.ACTIVE ? attendance.getRadius() : null; 

            return ResponseEntity.ok().body(new CheckAttendanceStatusResponseDTO(attendance.getStatus() == AttendanceStatus.ACTIVE, responseLatitude, responseLongitute, responseRadius));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
