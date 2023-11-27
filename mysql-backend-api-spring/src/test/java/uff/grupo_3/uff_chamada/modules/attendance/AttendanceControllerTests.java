package uff.grupo_3.uff_chamada.modules.attendance;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import uff.grupo_3.uff_chamada.config.SecurityFilter;

@WebMvcTest(controllers = AttendanceController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AttendanceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AttendanceService attendanceService;

    @MockBean
    private SecurityFilter securityFilter;

    @Test
    @WithMockUser
    public void getCheckAttendanceStatus() throws Exception{
        
        when(attendanceService.checkAttendanceStatus(1)).thenReturn(
            new Attendance(
                1, 
                1, 
                LocalDateTime.now(), 
                360000L, 
                22.910, 
                43.172, 
                100.0, 
                AttendanceStatus.ACTIVE
            )
        );

        String json = "{\"attendanceId\": 1}";
 
        mockMvc.perform(post("/attendance/checkAttendanceStatus")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.active", is(true)));
    }
}
