package uff.grupo_3.uff_chamada.modules.semester;

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
@RequestMapping("/semester")
public class SemesterController {
    private final SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService){
        this.semesterService = semesterService;
    }

    @GetMapping("/getSemester/{id}")
    public Semester getSemester(@PathVariable("id") int id){
        return this.semesterService.getSemester(id);
    }

    @PostMapping("/createSemester")
    public void createSemester(@RequestBody Semester semester){
        this.semesterService.createSemester(semester);
    }

    @PutMapping("/updateSemester")
    public void updateSemester(@RequestBody Semester semester){
        this.semesterService.updateSemester(semester);
    }

    @DeleteMapping("/deleteSemester/{id}")
    public void deleteSemester(@PathVariable("id") int id){
        this.semesterService.deleteSemester(id);
    }
}
