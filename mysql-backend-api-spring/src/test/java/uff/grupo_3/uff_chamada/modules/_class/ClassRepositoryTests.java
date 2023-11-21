package uff.grupo_3.uff_chamada.modules._class;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import uff.grupo_3.uff_chamada.modules.semester.Semester;
import uff.grupo_3.uff_chamada.modules.semester.SemesterRepository;
import uff.grupo_3.uff_chamada.modules.user.User;
import uff.grupo_3.uff_chamada.modules.user.UserRepository;
import uff.grupo_3.uff_chamada.modules.user.UserRole;

@DataJpaTest
// @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClassRepositoryTests {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private ClassRepository classRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
        semesterRepository.deleteAll();
        classRepository.deleteAll();
    }

    @Test
    public void shoudReturnListOfClasses(){

        // Arrange
        
        // criar user professor
        User professor = new User();
        professor.setId(1);
        professor.setUsername("professor");
        professor.setPassword("abc123");
        professor.setRole(UserRole.PROFESSOR);
        professor.setName("john");
        professor.setEmail("john@email.com");
        userRepository.save(professor);
        
        // criar semestre
        Semester semester = new Semester();
        semester.setId(1);
        semester.setYear("2023");
        semester.setSemester(1);
        semesterRepository.save(semester);
        
        // criar classe1
        Class _class1 = new Class();
        _class1.setId(1);
        _class1.setName("A1");
        _class1.setSubjectName("matematica");
        _class1.setProfessorId(1);
        _class1.setSemesterId(1);
        _class1.setSchedule("seg - qua / 9:00 - 11:00");
        classRepository.save(_class1);
        
        // criar classe2
        Class _class2 = new Class();
        _class2.setId(2);
        _class2.setName("B1");
        _class2.setSubjectName("algebra");
        _class2.setProfessorId(1);
        _class2.setSemesterId(1);
        _class2.setSchedule("ter - qui / 9:00 - 11:00");
        classRepository.save(_class2);


        //act
        //chamar metodo findClassByProfessorId
        List<Class> classes = classRepository.findClassByProfessorId(1);

        assertEquals(classes.size(), 2);

        //Assert
        // conferir se as classes retornadas são iguais as classes criadas
        assertEquals(classes.get(0).getId(), 1);
        assertEquals(classes.get(0).getName(), "A1");
        assertEquals(classes.get(0).getSubjectName(), "matematica");
        assertEquals(classes.get(0).getProfessorId(), 1);
        assertEquals(classes.get(0).getSchedule(), "seg - qua / 9:00 - 11:00");

        assertEquals(classes.get(1).getId(), 2);
        assertEquals(classes.get(1).getName(), "B1");
        assertEquals(classes.get(1).getSubjectName(), "algebra");
        assertEquals(classes.get(1).getProfessorId(), 1);
        assertEquals(classes.get(1).getSchedule(), "ter - qui / 9:00 - 11:00");
    }
}
