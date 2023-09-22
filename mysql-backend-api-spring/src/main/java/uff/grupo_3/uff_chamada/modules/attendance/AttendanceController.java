package uff.grupo_3.uff_chamada.modules.attendance;

import org.springframework.http.ResponseEntity;
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
@RequestMapping("/attedance")
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
    public ResponseEntity<Void> createAttedance(@RequestBody Attendance attendance){
        this.attendanceService.createAttedance(attendance);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/updateAttendance", produces = "aplication/json")
    public ResponseEntity<Void> updateAttendance(@RequestBody Attendance attendance){
        this.attendanceService.updateAttendance(attendance);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/deleteAttendance/{id}", produces = "aplication/json")
    public void deleteAttendance(@PathVariable("id") int id){
        this.attendanceService.deleteAttendance(id);
    }

    // TODO: criar endpoint para criar chamada, por default cria com status WAITING

    // TODO: criar endpoint para abrir chamada: body só com id (fazer validação se chamada existe) e só mudar stautus de chamada para ACTIVE

    // TODO: criar endpoint para fechar chamada: body só com id (fazer validação se chamada existe) e só mudar stautus de chamada para OVER
}
