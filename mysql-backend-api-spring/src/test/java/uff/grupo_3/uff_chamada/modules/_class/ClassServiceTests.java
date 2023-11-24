package uff.grupo_3.uff_chamada.modules._class;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClassServiceTests {
    
    @Mock
    private ClassRepository classRepository;

    @InjectMocks
    private ClassService classService;

    @Test
    public void shouldGetClassById(){
        // arrange
        Class _class = new Class();
        _class.setId(0);
        _class.setName("A1");
        _class.setSubjectName("prog 1");
        _class.setProfessorId(12);
        _class.setSemesterId(3);
        _class.setSchedule("seg e qua / 9:00 - 11:00");

        Optional<Class> optionalClass = Optional.of(_class);
        Mockito.when(classRepository.findById(anyInt())).thenReturn(optionalClass);

        //act
        Class expected = classService.getClass(0);

        //assert
        Assertions.assertThat(expected.getId()).isEqualTo(0);
    }

    @Test
    public void shouldGetClassByIdException(){
        // arrange
        int id = 0;
        when(classRepository.findById(id)).thenThrow(new IllegalStateException("classe de id " + id + " não existe"));

        //act
        //assert
        Assertions.assertThatThrownBy(() -> classService.getClass(0)).isInstanceOf(IllegalStateException.class).hasMessageContaining("classe de id 0 não existe");
    }

}
