package uff.grupo_3.uff_chamada.modules._class;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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



@WebMvcTest(controllers = ClassController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ClassControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ClassService classService;

    @MockBean
    private SecurityFilter securityFilter;


    @Test
    @WithMockUser
    public void getClassShouldReturnFoundClass() throws Exception{
        
        when(classService.getClass(1))
            .thenReturn(new Class(1,"A1","prog 1",1,1,"seg - qua / 9:00 - 11:00"));
            
            
        mockMvc.perform(get("/class/getClass/{id}", 1).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)));

    }

}
