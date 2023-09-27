package uff.grupo_3.uff_chamada.modules.justification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Justification", description = "Justification Requests")
@RestController
@RequestMapping("/justification")
public class JustificationController {

    private JustificationService justificationService;

    @Autowired
    public JustificationController(JustificationService justificationService){
        this.justificationService = justificationService;
    }

    @GetMapping(path = "/getJustification/{id}", produces = "application/json")
    @ResponseBody
    public Justification getJustification(@PathVariable("id") int id){
        return this.justificationService.getJustification(id);
    }

    @PostMapping(path = "/createJustification", consumes = "application/json")
    public void createJustification(Justification justification){
        this.justificationService.createJustification(justification);
    }

    @PutMapping(path = "/updateJustification", consumes = "application/json")
    public void updateJustification(Justification justification){
        this.justificationService.updateJustication(justification);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteJustication(@PathVariable("id") int id){
        this.justificationService.deleteJustication(id);
    }
}
