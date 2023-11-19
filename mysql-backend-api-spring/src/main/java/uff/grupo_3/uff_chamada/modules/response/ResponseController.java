package uff.grupo_3.uff_chamada.modules.response;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Response", description = "Response Requests")
@Controller
@RequestMapping(path = "/response")
public class ResponseController {
    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService){
        this.responseService = responseService;
    }

    @GetMapping(path = "/getResponse/{id}")
    @ResponseBody
    public Response getResponse(@PathVariable("id") int id){
        return responseService.getResponse(id);
    }

    @GetMapping(path = "/listResponse")
    @ResponseBody
    public List<Response> listResponse(){
        return responseService.listResponse();
    }

    @GetMapping(path = "/attendanceResponse")
    @ResponseBody
    public Map<String, List<String>> attendanceResponse(@RequestBody Response response){
        return responseService.attendanceResponse(response);
    }

    @PostMapping(path = "/createResponse")
    public ResponseEntity<String> createResponse(@RequestBody Response response){
        boolean result = this.responseService.createResponse(response);
        if(result){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().body("Chamada fechada");
        }
    }

    @PutMapping(path = "/updateResponse")
    public ResponseEntity<Void> updateResponse(@RequestBody Response response){
        this.responseService.updateResponse(response);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/validateResponse")
    public ResponseEntity<String> validateResponse(@RequestBody Response response, @AuthenticationPrincipal UserDetails userDetails){
        String userRole = userDetails.getAuthorities().iterator().next().getAuthority();
        if("ROLE_ADMIN".equals(userRole)){
            this.responseService.validateResponse(response);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Somente professor pode validar a presença");
    }

    @DeleteMapping(path = "/deleteResponse/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable("id") int id){
        this.responseService.deleteResponse(id);
        return ResponseEntity.ok().build();
    }


}
