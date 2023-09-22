package uff.grupo_3.uff_chamada.modules.enrollment;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uff.grupo_3.uff_chamada.modules.enrollment.dto.request.StudentEnrollmentsRequestDto;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentResponseDto;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // @PostMapping("/getStudentEnrollments")
    // @ResponseBody
    // public ResponseEntity<List<Enrollment>> getStudentEnrollments(
    //         @RequestBody StudentEnrollmentsRequestDto request) {
    //     return ResponseEntity.ok().body(enrollmentService.getStudentEnrollments(request.studentId()));
    // }

    // @PostMapping("/getStudentEnrollments")
    // @ResponseBody
    // public ResponseEntity<List<StudentEnrollmentResponseDto>> getStudentEnrollments(
    //         @RequestBody StudentEnrollmentsRequestDto request) {
    //     return ResponseEntity.ok().body(enrollmentService.getStudentEnrollments(request.studentId()));
    // }

    @PostMapping("/getStudentEnrollments")
    @ResponseBody
    public ResponseEntity<List<StudentEnrollmentResponseDto>> getStudentEnrollments(
            @RequestBody StudentEnrollmentsRequestDto request) {
                try{
                    return ResponseEntity.ok().body(enrollmentService.getStudentEnrollments(request.studentId()));
                } catch (Exception e){
                    return ResponseEntity.badRequest().build();
                }
    }

    @PostMapping("/createEnrollment")
    public void createEnrollment(@RequestBody Enrollment enrollment) {
        this.enrollmentService.createEnrollment(enrollment);
    }

    @DeleteMapping("/deleteEnrollment/{id}")
    public void deleteEnrollment(@PathVariable("id") int id) {
        this.enrollmentService.deleteEnrollment(id);
    }
}
