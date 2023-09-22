package uff.grupo_3.uff_chamada.modules.enrollment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uff.grupo_3.uff_chamada.modules._class.Class;
import uff.grupo_3.uff_chamada.modules._class.ClassRepository;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentResponseDto;
import uff.grupo_3.uff_chamada.modules.user.User;
import uff.grupo_3.uff_chamada.modules.user.UserRepository;

@Service
public class EnrollmentService {
    
    private EnrollmentRepository enrollmentRepository;
    private UserRepository userRepository;
    private ClassRepository classRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, ClassRepository classRepository){
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.classRepository = classRepository;
    }

    // public List<Enrollment> getStudentEnrollments(int studentId){
    //     return enrollmentRepository.findByStudentId(studentId).orElseGet(() -> new ArrayList<Enrollment>());
    // }

    // public List<StudentEnrollmentResponseDto> getStudentEnrollments(int studentId){
    //     return enrollmentRepository.findStudentEnrollments(studentId).orElseGet(() -> new ArrayList<StudentEnrollmentResponseDto>());
    // }

    public List<StudentEnrollmentResponseDto> getStudentEnrollments(int studentId){
        User user = userRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("nao existe usuario"));
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId).orElseGet(() -> new ArrayList<Enrollment>());

        List<StudentEnrollmentResponseDto> enrollmentsDto = new ArrayList<>();
        
        for (Enrollment enrollment : enrollments){
            
            Class _class = classRepository.findById(enrollment.getClassId()).orElseThrow(() -> new IllegalStateException("classe não existe"));
            
            StudentEnrollmentResponseDto enrollmentResponseDto = new StudentEnrollmentResponseDto(
                enrollment.getId(),
                enrollment.getStudentId(),
                user.getName(),
                enrollment.getClassId(),
                _class.getName(),
                enrollment.getCreatedAt()
            );

            enrollmentsDto.add(enrollmentResponseDto);
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
