package uff.grupo_3.uff_chamada._class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
