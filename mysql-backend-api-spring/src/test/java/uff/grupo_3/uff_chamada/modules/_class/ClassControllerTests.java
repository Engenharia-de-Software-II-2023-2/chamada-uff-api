package uff.grupo_3.uff_chamada.modules._class;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.type.TypeReference;

import uff.grupo_3.uff_chamada.config.SecurityFilter;



@WebMvcTest(ClassController.class)
// @ComponentScan(excludeFilters = {@ComponentScan.Filter(type=FilterType.CUSTOM,classes=TypeExcludeFilter.class), @ComponentScan.Filter(type=FilterType.CUSTOM,classes=SecurityFilter.class)})

public class ClassControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassService classService;

    @MockBean
    private SecurityFilter securityFilter;


    @Test
    @WithMockUser
    public void getClassShouldReturnFoundClass() throws Exception{
        
        when(classService.getClass(1))
            .thenReturn(
                new Class(
                    1,
                    "A1",
                    "prog 1",
                    1,
                    1,
                    "seg - qua / 9:00 - 11:00"
                )
            );

        MockHttpServletResponse response = mockMvc.perform(get("/class/getClass/{id}", 1))
            .andDo(print())
            // .andExpect(status().isOk())
            // .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn().getResponse();
        
        String responseString = response.getContentAsString();

        // Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        // HashMap<String, Object> body = objectMapper.readValue(responseString, new TypeReference<HashMap<String, Object>>() {});

        // Assertions.assertThat(body.get("id")).isEqualTo(1);
        // Assertions.assertThat(body.get("name")).isEqualTo("A1");
    }

    // @Test
    // @WithMockUser
    // public void professorClassesShouldReturnListOfClasses(){

    //     when(classService.)
    // }
}
