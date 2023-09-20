package uff.grupo_3.uff_chamada.modules.justification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JustificationService {
    private JustificationRepository justificationRepository;

    @Autowired
    public JustificationService(JustificationRepository justificationRepository){
        this.justificationRepository = justificationRepository;
    }

    public Justification getJustification(int id){
        return this.justificationRepository.findById(id).orElseThrow(() -> new IllegalStateException("justificativa de id " + id + " não existe"));
    }

    public void createJustification(Justification justification){
        this.justificationRepository.save(justification);
    }

    public void updateJustication(Justification justication){
        this.justificationRepository.save(justication);
    }

    public void deleteJustication(int id){
        this.justificationRepository.deleteById(id);
    }
}
