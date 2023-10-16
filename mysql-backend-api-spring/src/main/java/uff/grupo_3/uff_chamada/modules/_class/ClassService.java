package uff.grupo_3.uff_chamada.modules._class;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uff.grupo_3.uff_chamada.modules._class.dto.response.ProfessorClassesListItemResponseDto;
import uff.grupo_3.uff_chamada.modules._class.dto.response.ProfessorClassesListResponseDto;
import uff.grupo_3.uff_chamada.modules.semester.Semester;
import uff.grupo_3.uff_chamada.modules.semester.SemesterRepository;
import uff.grupo_3.uff_chamada.modules.user.UserRepository;

@Service
public class ClassService {
    private final ClassRepository classRepository;
    private final SemesterRepository semesterRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClassService(ClassRepository classRepository, SemesterRepository semesterRepository, UserRepository userRepository){
        this.classRepository = classRepository;
        this.semesterRepository = semesterRepository;
        this.userRepository = userRepository;
    }

    public Class getClass(int id){
        return this.classRepository.findById(id).orElseThrow(() -> new IllegalStateException("classe de id " + id + " não existe"));
    }

    public void createClass(Class _class){
        this.classRepository.save(_class);
        // this.classRepository.saveClass(_class.getId(), _class.getName(), _class.getProfessorId(), _class.getSemesterId());
    }

    public void updateClass(Class _class){
        this.classRepository.save(_class);
    }

    public void deleteClass(int id){
        this.classRepository.deleteById(id);
    }

    public ProfessorClassesListResponseDto professorClasses(int professorId, String year, int semester){

        if (userRepository.findById(professorId).isEmpty()){
            return null;
        }

        try {
            List<Class> professorClasses = this.classRepository.findClassByProfessorId(professorId);
            Semester currentSemester = semesterRepository.findSemesterByYearAndSemester(year, semester).orElseThrow(() -> new IllegalStateException("ano ou semestre invalidos"));

            ProfessorClassesListResponseDto professorClassesDto = new ProfessorClassesListResponseDto();

            for (Class _class : professorClasses){

                Semester classSemester = semesterRepository.findById(_class.getSemesterId()).get();

                if (currentSemester.getId() == classSemester.getId()){

                    ProfessorClassesListItemResponseDto item = new ProfessorClassesListItemResponseDto(
                        _class.getId(),
                        _class.getName(),
                        _class.getSubjectName(),
                        _class.getSchedule(),
                        currentSemester.getId(),
                        currentSemester.getYear(),
                        currentSemester.getSemester(),
                        false
                    );
                    professorClassesDto.getProfessorClasses().add(item);
                }


            }

            return professorClassesDto;
        } catch (Exception e){
            return null;
        }
        
        
    }
}
