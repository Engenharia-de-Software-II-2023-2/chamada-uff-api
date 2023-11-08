package uff.grupo_3.uff_chamada.modules.enrollment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uff.grupo_3.uff_chamada.modules._class.Class;
import uff.grupo_3.uff_chamada.modules._class.ClassRepository;
import uff.grupo_3.uff_chamada.modules.attendance.Attendance;
import uff.grupo_3.uff_chamada.modules.attendance.AttendanceRepository;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentListDto;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentEnrollmentListItemDto;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentRecordList;
import uff.grupo_3.uff_chamada.modules.enrollment.dto.response.StudentRecordListItem;
import uff.grupo_3.uff_chamada.modules.response.Response;
import uff.grupo_3.uff_chamada.modules.response.ResponseRepository;
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
    private AttendanceRepository attendanceRepository;
    private ResponseRepository responseRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, ClassRepository classRepository, SemesterRepository semesterRepository, AttendanceRepository attendanceRepository, ResponseRepository responseRepository){
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.classRepository = classRepository;
        this.semesterRepository = semesterRepository;
        this.attendanceRepository = attendanceRepository;
        this.responseRepository = responseRepository;
    }

    public StudentEnrollmentListDto getStudentEnrollments(int studentId, String year, int semester){
        try {
            User user = userRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("nao existe usuario"));
            Semester currentSemester = this.semesterRepository.findSemesterByYearAndSemester(year, semester).orElseThrow(() -> new IllegalStateException("semestre com ano ou semestre invalidos"));
            List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId).orElseGet(() -> new ArrayList<Enrollment>());

            StudentEnrollmentListDto enrollmentsDto = new StudentEnrollmentListDto();
            
            for (Enrollment enrollment : enrollments){
                
                Class _class = classRepository.findById(enrollment.getClassId()).orElseThrow(() -> new IllegalStateException("classe não existe"));
                User professor = this.userRepository.findById(_class.getProfessorId()).get();
                Semester classSemester = this.semesterRepository.findById(_class.getSemesterId()).get();
                List<Attendance> openAttendances = attendanceRepository.findOpenAttendances(_class.getId());
                boolean isActive = openAttendances.size() != 0;

                if (openAttendances.size() != 0) {}

                if (currentSemester.getId() == classSemester.getId()){
                    StudentEnrollmentListItemDto enrollmentItem = new StudentEnrollmentListItemDto(
                        enrollment.getId(),
                        _class.getId(),
                        _class.getName(),
                        _class.getSubjectName(),
                        _class.getSchedule(),
                        professor.getId(),
                        professor.getName(),
                        currentSemester.getId(),
                        currentSemester.getYear(),
                        currentSemester.getSemester(),
                        isActive,
                        isActive == true ? openAttendances.get(0).getId() : null
                    );

                    enrollmentsDto.getEnrollments().add(enrollmentItem);
                }
            }

        return enrollmentsDto;
        } catch (Exception e) {
            return null;
        }

    }

    public void createEnrollment(Enrollment enrollment){
        enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(int id){
            enrollmentRepository.deleteById(id);
        }

    public StudentRecordList checkStudentAttendanceRecord(int studentId, int classId){
        StudentRecordList attendanceRecord = new StudentRecordList();

        List<Attendance> attendanceList = attendanceRepository.findByClassId(classId);
        List<Response> responseList = responseRepository.findByStudentId(studentId);

        for (Attendance attendance : attendanceList){

            boolean wasPresent = false;

            for (Response response : responseList) {

                if (attendance.getId() == response.getAttendanceId()){
                    wasPresent = true;
                    break;
                }
            }

            StudentRecordListItem item = new StudentRecordListItem(attendance.getId(), studentId, attendance.getClassId(), attendance.getStart(), wasPresent);
            attendanceRecord.getAttendanceRecordList().add(item);
        }

        return attendanceRecord;
    }

}

