package uff.grupo_3.uff_chamada.modules.enrollment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uff.grupo_3.uff_chamada.modules._class.Class;
import uff.grupo_3.uff_chamada.modules._class.ClassRepository;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentListDto;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentListItemDto;
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

    // public List<Enrollment> getStudentEnrollments(int studentId){
    //     return enrollmentRepository.findByStudentId(studentId).orElseGet(() -> new ArrayList<Enrollment>());
    // }

    // public List<StudentEnrollmentResponseDto> getStudentEnrollments(int studentId){
    //     return enrollmentRepository.findStudentEnrollments(studentId).orElseGet(() -> new ArrayList<StudentEnrollmentResponseDto>());
    // }

    public StudentEnrollmentListDto getStudentEnrollments(int studentId){
        User user = userRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("nao existe usuario"));
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId).orElseGet(() -> new ArrayList<Enrollment>());

        StudentEnrollmentListDto enrollmentsDto = new StudentEnrollmentListDto();
        
        for (Enrollment enrollment : enrollments){
            
            Class _class = classRepository.findById(enrollment.getClassId()).orElseThrow(() -> new IllegalStateException("classe não existe"));
            User professor = this.userRepository.findById(_class.getProfessorId()).get();
            Semester semester = this.semesterRepository.findById(_class.getSemesterId()).get();
            
            StudentEnrollmentListItemDto enrollmentItem = new StudentEnrollmentListItemDto(
                enrollment.getId(),
                _class.getId(),
                _class.getName(),
                _class.getSubjectName(),
                _class.getSchedule(),
                professor.getId(),
                professor.getName(),
                semester.getId(),
                semester.getYear(),
                semester.getSemester(),
                false
            );

            enrollmentsDto.getEnrollments().add(enrollmentItem);
        }

        return enrollmentsDto;
    }

    public void createEnrollment(Enrollment enrollment){
        enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(int id){
        enrollmentRepository.deleteById(id);
    }
}
