package uff.grupo_3.uff_chamada.modules.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import uff.grupo_3.uff_chamada.modules.response.DTO.response.CheckResponseDTO;
// import uff.grupo_3.uff_chamada.modules.user.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ResponseServiceTests {
    @Mock
    private ResponseRepository responseRepository;
    // private UserRepository userRepository;

    @InjectMocks
    private ResponseService responseService;

    @Test
    public void shouldFindByStudentIdAttendanceId(){
        Response response = new Response();
        response.setId(4);
        response.setAttendanceId(1);
        response.setStudentId(1);

        Mockito.when(responseRepository.findByStudentIdAttendanceId(anyInt(), anyInt())).thenReturn(java.util.Optional.of(response));

        CheckResponseDTO actualResponse = responseService.findByStudentIdAttendanceId(1, 1);

        Assertions.assertThat(actualResponse).isNotNull();
        
        assertEquals(response.getId(), actualResponse.getResponseId());
        assertEquals(response.getAttendanceId(), actualResponse.getAttendanceId());
        assertEquals(response.getStudentId(), actualResponse.getStudentId());
    }

    // @Test
    // public void shouldFindAttendanceResponse(){
    //     Response response1 = new Response();
    //     response1.setId(4);
    //     response1.setAttendanceId(1);
    //     response1.setStudentId(1);

    //     Response response2 = new Response();
    //     response2.setId(5);
    //     response2.setAttendanceId(1);
    //     response2.setStudentId(2);

    //     Response response3 = new Response();
    //     response3.setId(6);
    //     response3.setAttendanceId(2);
    //     response3.setStudentId(1);

    //     Map<String, List<Map<String, Object>>> expectedResponses = new HashMap<>();
    //     ArrayList<Map<String, Object>> present = new ArrayList<Map<String, Object>>();
    //     ArrayList<Map<String, Object>> absent = new ArrayList<Map<String, Object>>();

    //     Map<String, Object> response1Map = new HashMap<>();
    //     response1Map.put("name", "Matheus"); // userRepository.findById(response1.getStudentId()).get().getName()
    //     response1Map.put("id", response1.getId());

    //     Map<String, Object> response2Map = new HashMap<>();
    //     response2Map.put("name", "Lucas"); // userRepository.findById(response2.getStudentId()).get().getName()
    //     response2Map.put("id", response2.getId());

    //     Map<String, Object> response3Map = new HashMap<>();
    //     response3Map.put("name", "Pedro"); // userRepository.findById(response3.getStudentId()).get().getName()
    //     response3Map.put("id", response3.getId());

    //     present.add(response1Map);
    //     present.add(response2Map);
    //     absent.add(response3Map);

    //     expectedResponses.put("present", present);
    //     expectedResponses.put("absent", absent); 

    //     // Mockito.when(responseRepository.findAllByAttendanceId(anyInt())).thenReturn(expectedResponses); // aqui ta quebrado

    //     Response resp = new Response();
    //     resp.setAttendanceId(1);
    //     Map<String, List<Map<String, Object>>> actualResponses = responseService.attendanceResponse(resp);

    //     Assertions.assertThat(actualResponses).isNotNull();
        
    //     assertEquals(expectedResponses.size(), actualResponses.size());

    //     assertEquals(expectedResponses.get("present").get(0).get("name"), actualResponses.get("present").get(0).get("name"));
    //     assertEquals(expectedResponses.get("present").get(0).get("id"), actualResponses.get("present").get(0).get("id"));
    // }
}
