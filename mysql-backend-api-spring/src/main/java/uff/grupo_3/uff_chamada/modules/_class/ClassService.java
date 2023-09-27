package uff.grupo_3.uff_chamada.modules._class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    private final ClassRepository classRepository;

    @Autowired
    public ClassService(ClassRepository classRepository){
        this.classRepository = classRepository;
    }

    public Class getClass(int id){
        return this.classRepository.findById(id).orElseThrow(() -> new IllegalStateException("classe de id " + id + " não existe"));
    }

    public void createClass(Class _class){
        this.classRepository.save(_class);
        // this.classRepository.saveClass(_class.getId(), _class.getName(), _class.getProfessorId(), _class.getSemesterId());
    }

    public void updateClass(Class _class){
        this.classRepository.save(_class);
    }

    public void deleteClass(int id){
        this.classRepository.deleteById(id);
    }
}
