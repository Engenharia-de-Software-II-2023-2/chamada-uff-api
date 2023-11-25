package uff.grupo_3.uff_chamada.modules.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import uff.grupo_3.uff_chamada.modules.attendance.dto.response.AttendancesByClassResponseDTO;

@ExtendWith(MockitoExtension.class)
public class AttendanceServiceTests {
    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceService attendanceService;

    @Test
    public void shouldFindByClassById(){
        Attendance attendance1 = new Attendance();
        attendance1.setId(4);
        attendance1.setClassId(1);
        attendance1.setStatus(AttendanceStatus.WAITING);

        Attendance attendance2 = new Attendance();
        attendance2.setId(5);
        attendance2.setClassId(1);
        attendance2.setStatus(AttendanceStatus.WAITING);

        Attendance attendance3 = new Attendance();
        attendance3.setId(6);
        attendance3.setClassId(2);
        attendance3.setStatus(AttendanceStatus.WAITING);

        List<Attendance> expectedAttendances = Arrays.asList(attendance1, attendance2);

        // Step 3: Use Mockito.when() to simulate the behavior of findByClassId()
        Mockito.when(attendanceRepository.findByClassId(anyInt())).thenReturn(expectedAttendances);

        AttendancesByClassResponseDTO actualDTO = attendanceService.findAttendancesByClass(1);

        // Verificar se o DTO retornado não é nulo
        Assertions.assertThat(actualDTO).isNotNull();
        
        // Verificar se a lista de Attendance dentro do DTO tem o mesmo tamanho que a lista esperada
        assertEquals(expectedAttendances.size(), actualDTO.getAttendances().size());
        
        assertEquals(expectedAttendances.get(0).getId(), actualDTO.getAttendances().get(0).getId());
        assertEquals(expectedAttendances.get(0).getClassId(), actualDTO.getAttendances().get(0).getClassId());
        assertEquals(expectedAttendances.get(0).getStatus(), actualDTO.getAttendances().get(0).getStatus());

        assertEquals(expectedAttendances.get(1).getId(), actualDTO.getAttendances().get(1).getId());
        assertEquals(expectedAttendances.get(1).getClassId(), actualDTO.getAttendances().get(1).getClassId());
        assertEquals(expectedAttendances.get(1).getStatus(), actualDTO.getAttendances().get(1).getStatus());
    }
}