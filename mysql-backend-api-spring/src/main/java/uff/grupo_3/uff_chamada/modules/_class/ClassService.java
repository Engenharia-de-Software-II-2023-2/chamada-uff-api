package uff.grupo_3.uff_chamada.modules._class;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uff.grupo_3.uff_chamada.modules._class.dto.response.CurrentProfessorClassesResponseDto;
import uff.grupo_3.uff_chamada.modules.semester.Semester;
import uff.grupo_3.uff_chamada.modules.semester.SemesterRepository;
import uff.grupo_3.uff_chamada.modules.user.User;
import uff.grupo_3.uff_chamada.modules.user.UserRepository;

@Service
public class ClassService {
    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final SemesterRepository semesterRepository;

    @Autowired
    public ClassService(
        ClassRepository classRepository,
        UserRepository userRepository,
        SemesterRepository semesterRepository
    ){
        this.classRepository = classRepository;
        this.userRepository = userRepository;
        this.semesterRepository = semesterRepository;
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

    public CurrentProfessorClassesResponseDto currentProfessorClasses(String year, int semester, String professorName){
        CurrentProfessorClassesResponseDto professorClasses = new CurrentProfessorClassesResponseDto();

        List<Class> classes = classRepository.findAll();
        List<Semester> semesters = semesterRepository.findByYear(year).orElseGet(() -> new ArrayList<>());
        List<User> professors = userRepository.findByName(professorName).orElseGet(() -> new ArrayList<>());

        for (Class c : classes){
            for (Semester s : semesters) {
                for (User p : professors ) {

                    if (c.getSemesterId() == s.getId() && c.getProfessorId() == p.getId()) {
                        if (s.getSemester() == semester && p.getName().equals(professorName)){
                            professorClasses.getClasses().add(c);
                        }
                    }
                }
            }
        }
        return professorClasses;
    }
}
