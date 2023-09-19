package uff.grupo_3.uff_chamada.attendance;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/attedance")
public class AttendanceController {
    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }


    @GetMapping(path = "/getAttendance/{id}", produces = "application/json")
    @ResponseBody
    public Attendance getAttendance(@PathVariable("id") int id){
        return this.attendanceService.getAttendance(id);
    }

    @PostMapping(path = "/createAttendance", produces = "aplication/json")
    public void createAttedance(@RequestBody Attendance attendance){
        this.attendanceService.createAttedance(attendance);
    }

    @PutMapping(path = "/updateAttendance", produces = "aplication/json")
    public void updateAttendance(@RequestBody Attendance attendance){
        this.attendanceService.updateAttendance(attendance);
    }

    @DeleteMapping(path = "/deleteAttendance/{id}", produces = "aplication.json")
    public void deleteAttendance(@PathVariable("id") int id){
        this.attendanceService.deleteAttendance(id);
    }

}
