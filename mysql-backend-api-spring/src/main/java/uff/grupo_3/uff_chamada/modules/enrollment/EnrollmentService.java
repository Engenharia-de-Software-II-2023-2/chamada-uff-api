package uff.grupo_3.uff_chamada.modules.enrollment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uff.grupo_3.uff_chamada.modules._class.Class;
import uff.grupo_3.uff_chamada.modules._class.ClassRepository;
import uff.grupo_3.uff_chamada.modules.attendance.Attendance;
import uff.grupo_3.uff_chamada.modules.attendance.AttendanceRepository;
import uff.grupo_3.uff_chamada.modules.attendance.AttendanceStatus;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentResponseListDto;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentResponseListItemDto;
import uff.grupo_3.uff_chamada.modules.semester.Semester;
import uff.grupo_3.uff_chamada.modules.semester.SemesterRepository;
import uff.grupo_3.uff_chamada.modules.user.User;
import uff.grupo_3.uff_chamada.modules.user.UserRepository;

@Service
public class EnrollmentService {
    
    private EnrollmentRepository enrollmentRepository;
    private UserRepository userRepository;
    private ClassRepository classRepository;
    private SemesterRepository semesterRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, ClassRepository classRepository, SemesterRepository semesterRepository){
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.classRepository = classRepository;
        this.semesterRepository = semesterRepository;
    }

    public StudentEnrollmentResponseListDto getStudentEnrollments(int studentId){
        StudentEnrollmentResponseListDto enrollmentsList = new StudentEnrollmentResponseListDto();

        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId).orElseGet(() -> new ArrayList<Enrollment>());

        for (Enrollment enrollment : enrollments){

            Class _class = classRepository.findById(enrollment.getClassId()).orElseThrow(() -> new IllegalStateException("classe não existe"));
            User professor = userRepository.findById(_class.getId()).get();
            Semester semester = semesterRepository.findById(_class.getId()).get();

            StudentEnrollmentResponseListItemDto enrollmentItem = new StudentEnrollmentResponseListItemDto(
                enrollment.getId(),
                _class.getId(),
                _class.getName(),
                _class.getSubjectName(),
                _class.getProfessorId(),
                professor.getName(),
                semester.getId(),
                semester.getYear(),
                semester.getSemester()
            );

            enrollmentsList.getEnrollments().add(enrollmentItem);

        }
        
            return enrollmentsList;
        }

    public void createEnrollment(Enrollment enrollment){
        enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(int id){
            enrollmentRepository.deleteById(id);
        }

}

