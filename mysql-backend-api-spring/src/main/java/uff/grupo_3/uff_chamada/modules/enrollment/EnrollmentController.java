package uff.grupo_3.uff_chamada.modules.enrollment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uff.grupo_3.uff_chamada.modules.enrollment.dto.request.StudentEnrollmentsRequestDto;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.request.studentRecordRequestDTO;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentListDto;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentRecordList;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Enrollment", description = "Enrollment Requests")
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
    public ResponseEntity getStudentEnrollments(
            @RequestBody StudentEnrollmentsRequestDto request) {

                StudentEnrollmentListDto response = enrollmentService.getStudentEnrollments(request.getStudentId(), request.getYear(), request.getSemester());

                if (response != null){
                    return ResponseEntity.ok().body(response);
                }

                return ResponseEntity.badRequest().body("parametros invalidos");
            
    }

    @GetMapping(path = "/classEnrollments/{classId}")
    @ResponseBody
    public List<Enrollment> classEnrollments(@PathVariable("classId") int classId){
        return enrollmentService.classEnrollments(classId);
    }

    @PostMapping("/createEnrollment")
    public void createEnrollment(@RequestBody Enrollment enrollment) {
        this.enrollmentService.createEnrollment(enrollment);
    }

    @DeleteMapping("/deleteEnrollment/{id}")
    public void deleteEnrollment(@PathVariable("id") int id) {
        this.enrollmentService.deleteEnrollment(id);
    }

    @PostMapping(path = "/checkStudentAttendanceRecord")
    public ResponseEntity<StudentRecordList> checkStudentAttendanceRecord(@RequestBody studentRecordRequestDTO request){
        try {
            return ResponseEntity.ok().body(enrollmentService.checkStudentAttendanceRecord(request.getStudentId(), request.getClassId()));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
