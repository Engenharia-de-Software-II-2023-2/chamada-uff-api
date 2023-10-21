package uff.grupo_3.uff_chamada.modules.attendance;

import java.util.List;

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

@Tag(name = "Attendance", description = "Attendance Requests")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    //ROLE_ADMIN == PROFESSOR
    //ROLE_USER == STUDENT

    @GetMapping(path = "/getAttendance/{id}", produces = "application/json")
    @ResponseBody
    public Attendance getAttendance(@PathVariable("id") int id){
        return this.attendanceService.getAttendance(id);
    }

    @PostMapping(path = "/createAttendance/{classId}", produces = "aplication/json")
    public ResponseEntity<Void> createAttendance(@PathVariable("classId") int classId, @AuthenticationPrincipal UserDetails userDetails){
        String userRole = userDetails.getAuthorities().iterator().next().getAuthority();
        if("ROLE_ADMIN".equals(userRole)){
            this.attendanceService.createAttendance(classId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(path = "/updateAttendance", produces = "aplication/json")
    public ResponseEntity<Void> updateAttendance(@RequestBody Attendance attendance, @AuthenticationPrincipal UserDetails userDetails){
        String userRole = userDetails.getAuthorities().iterator().next().getAuthority();
        if("ROLE_ADMIN".equals(userRole)){
            this.attendanceService.updateAttendance(attendance);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(path = "/controlAttendance", produces = "aplication/json")
    public ResponseEntity<Void> controlAttendance(@RequestBody Attendance attendance, @AuthenticationPrincipal UserDetails userDetails){
        String userRole = userDetails.getAuthorities().iterator().next().getAuthority();
        if("ROLE_ADMIN".equals(userRole)){
            this.attendanceService.controlAttendance(attendance);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "/deleteAttendance/{id}", produces = "aplication/json")
    public ResponseEntity<Void> deleteAttendance(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails){
        String userRole = userDetails.getAuthorities().iterator().next().getAuthority();
        if("ROLE_ADMIN".equals(userRole)){
            this.attendanceService.deleteAttendance(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping(path = "/createWaitingAttendance")
    public ResponseEntity<Attendance> createWaitingAttendance(@RequestBody Attendance attendance){
        return ResponseEntity.ok((attendanceService.createWaitingAttendance(attendance))); 
    }

    @PostMapping(path = "/createActiveAttendance")
    public ResponseEntity<Attendance> createActiveAttendance(@RequestBody Attendance attendance){
        return ResponseEntity.ok(attendanceService.createActiveAttendance(attendance));
    }

    @GetMapping(path = "/getActiveAttendances/{id}")
    @ResponseBody
    public List<Attendance> getActiveAttendances(@PathVariable("id") int id){
        return this.attendanceService.getActiveAttendances(id);
    }

    @GetMapping(path = "/findAttendancesByClassId/{id}")
    @ResponseBody
    public List<Attendance> findAttendancesByClassId(@PathVariable("id") int id ){
        return this.attendanceService.getAttendancesByClassId(id);
    }

}
