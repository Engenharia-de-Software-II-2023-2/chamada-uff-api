package uff.grupo_3.uff_chamada.modules.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/response")
public class ResponseController {
    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService){
        this.responseService = responseService;
    }

    @GetMapping(path = "/getResponse/{id}")
    public Response getResponse(@PathVariable("id") int id){
        return responseService.getResponse(id);
    }

    @PostMapping(path = "/createResponse")
    public ResponseEntity<Void> createResponse(@RequestBody Response response){
        this.responseService.createResponse(response);
        return ResponseEntity.ok().build();
    }

    // TODO: Criar endpoint especifico para registrar presença: com validação de se a chamada esta com status ACTIVE e se já m foi registrada a presença do aluno

    @PutMapping(path = "/updateResponse")
    public ResponseEntity<Void> updateResponse(@RequestBody Response response){
        this.responseService.updateResponse(response);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/deleteResponse/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable("id") int id){
        this.responseService.deleteResponse(id);
        return ResponseEntity.ok().build();
    }
}
