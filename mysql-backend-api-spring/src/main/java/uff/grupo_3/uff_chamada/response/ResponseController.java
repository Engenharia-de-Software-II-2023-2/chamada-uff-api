package uff.grupo_3.uff_chamada.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public void createResponse(Response response){
        this.responseService.createResponse(response);
    }

    @PutMapping(path = "/updateResponse")
    public void updateResponse(Response response){
        this.responseService.updateResponse(response);
    }

    @DeleteMapping(path = "/deleteResponse/{id}")
    public void deleteResponse(@PathVariable("id") int id){
        this.deleteResponse(id);
    }
}
