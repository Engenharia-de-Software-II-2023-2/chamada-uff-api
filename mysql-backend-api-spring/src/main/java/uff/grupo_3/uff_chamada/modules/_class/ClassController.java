package uff.grupo_3.uff_chamada.modules._class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import uff.grupo_3.uff_chamada.modules._class.dto.request.ProfessorClassesRequestDto;
import uff.grupo_3.uff_chamada.modules._class.dto.response.ProfessorClassesListResponseDto;

@Tag(name = "Class", description = "Class Requests")
@RestController
@RequestMapping("/class")
public class ClassController {
    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService){
        this.classService = classService;
    }

    @GetMapping("/getClass/{id}")
    public Class getClass(@PathVariable("id") int id){
        return this.classService.getClass(id);
    }

    @PostMapping("/createClass")
    public void createClass(@RequestBody Class _class){
        this.classService.createClass(_class);
    }

    @PutMapping("/updateClass")
    public void updateClass(@RequestBody Class _class){
        this.classService.updateClass(_class);
    }

    @DeleteMapping("/deleteClass/{id}")
    public void deleteClass(@PathVariable("id") int id){
        this.classService.deleteClass(id);
    }

    @PostMapping(path = "/professsorClasses")
    public ResponseEntity professorClasses(@RequestBody ProfessorClassesRequestDto request){
        ProfessorClassesListResponseDto response = classService.professorClasses(request.getProfessorId(), request.getYear(), request.getSemester());
        
        if (response != null){
            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.badRequest().body("parametros invalidos");
    }
}
