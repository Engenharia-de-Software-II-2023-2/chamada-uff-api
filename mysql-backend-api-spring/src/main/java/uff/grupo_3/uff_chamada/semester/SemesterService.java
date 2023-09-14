package uff.grupo_3.uff_chamada.semester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository){
        this.semesterRepository = semesterRepository;
    }

    public Semester getSemester(int id){
        return this.semesterRepository.findById(id).orElseThrow(() -> new IllegalStateException("Semestre de id " + id + " não existe"));
    }

    public void createSemester(Semester semester){
        this.semesterRepository.save(semester);
    }

    public void updateSemester(Semester semester){
        this.semesterRepository.save(semester);
    }

    public void deleteSemester(int id){
        this.semesterRepository.deleteById(id);
    }
}
